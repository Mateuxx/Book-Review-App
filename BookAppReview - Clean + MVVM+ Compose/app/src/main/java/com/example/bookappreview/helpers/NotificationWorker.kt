package com.example.bookappreview.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bookappreview.R
import com.example.bookappreview.data.webclient.aiservice.AiService
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
    private val aiService: AiService
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("NotificationWorker", "Worker iniciado.")
                // Obter o último livro marcado como "like"
                val lastLikedBook = bookRepository.getLastLikedBook()
                // Obter recomendações
                val recommendations = aiService.fetchBookRecommendations(lastLikedBook.title ?: "")
                val notificationMessage = buildNotificationMessage(lastLikedBook.title, recommendations)
                // Exibir notificação
                showNotification(notificationMessage)

                Log.d("NotificationWorker", "Notificação exibida com sucesso.")
                Result.success()
            } catch (e: Exception) {
                Log.e("NotificationWorker", "Erro ao executar o Worker", e)
                Result.failure()
            }
        }
    }

    private fun buildNotificationMessage(bookTitle: String?, recommendations: String): String {
        return "Baseado no livro \"$bookTitle\", recomendamos:\n$recommendations"
    }

    private fun showNotification(message: String) {
        val channelId = "book_recommendations_channel"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Recomendações de Livros",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Recomendações de Livros")
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }
}