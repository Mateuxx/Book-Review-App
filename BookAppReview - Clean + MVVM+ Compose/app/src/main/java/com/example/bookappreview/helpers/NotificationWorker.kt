package com.example.bookappreview.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.bookappreview.R
import com.example.bookappreview.data.webclient.BookService
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val bookRepository: LivroRepository,
    private val aiService: AiService,
    private val bookService: BookService
) : CoroutineWorker(context, workerParams) {


    private val TAG = "NotificationWorker"

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("NotificationWorker", "Worker iniciado.")

                // Obter o último livro gostado
                val lastLikedBook = bookRepository.getLastLikedBook()
                Log.i("TAG", "lastLikedBook: $lastLikedBook")

                // Obter recomendações para o livro gostado
                val recommendations = aiService.fetchBookRecommendations(lastLikedBook.title ?: "")
                Log.i("TAG", "Recomendação: $recommendations")

                val parse = aiService.parseBookRecommendations(recommendations)
                Log.i(TAG, "parse:  $parse")

                if (recommendations.isNotEmpty()) {
                    //Pego o primeiro livro da lista gerada pelo parse
                    val firstRecommendation = parse[0]
                    Log.i(TAG, "firstRecommendation: $firstRecommendation")

                    // Buscar detalhes do livro recomendado usando a API
                    val recommendedBooks =
                        bookService.bookApi(firstRecommendation.toString(), context)
                    Log.i("TAG", "recommendedBooks: $recommendedBooks")
                    if (recommendedBooks.isNotEmpty()) {
                        val recommendedBook = recommendedBooks[0] // Primeiro livro encontrado
                        Log.i("TAG", "FirstrecommendedBook: $recommendedBook")
                        val bookImage =
                            recommendedBook.imagem?.let { getBitmapFromUrl(it) } // Carrega a imagem

                        // Exibir notificação
                        showNotification(lastLikedBook, recommendedBook, bookImage)
                    }
                }

                Log.d("NotificationWorker", "Notificação exibida com sucesso.")
                Result.success()
            } catch (e: Exception) {
                Log.e("NotificationWorker", "Erro ao executar o Worker", e)
                Result.failure()
            }
        }
    }

    private fun showNotification(likedBook: Livro, recommendedBook: Livro, bookImage: Bitmap?) {
        val channelId = "book_recommendations_channel"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Criar o canal de notificação, se necessário (Android O+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Recomendações de Livros",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description =
                    "Notificações sobre recomendações de livros baseadas nos seus interesses."
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Mensagem personalizada
        val message =
            "📚 Porque você gostou de \"${likedBook.title}\", recomendamos \"${recommendedBook.title}\". Descubra mais sobre este incrível livro! 🚀"

        // Configuração da notificação
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Recomendações de Livros")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        // Adicionar imagem na notificação, se disponível
        bookImage?.let { bitmap ->
            notificationBuilder.setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
                    .bigLargeIcon(null as Bitmap?) // Define explicitamente o tipo Bitmap para evitar ambiguidade
                    .setSummaryText(message)
            )
        }

        val notification = notificationBuilder.build()
        notificationManager.notify(1, notification)
    }

    // Método para carregar a imagem dinamicamente
    private suspend fun getBitmapFromUrl(imageUrl: String): Bitmap? {
        return try {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .size(coil.size.Size.ORIGINAL) // Carregar a imagem no tamanho original
                .allowHardware(true) // Evitar compressão adicional ou n?
                .build()

            val result = (loader.execute(request) as SuccessResult).drawable
            (result as? BitmapDrawable)?.bitmap
        } catch (e: Exception) {
            Log.e("NotificationWorker", "Erro ao carregar a imagem: $imageUrl", e)
            null
        }
    }

}
