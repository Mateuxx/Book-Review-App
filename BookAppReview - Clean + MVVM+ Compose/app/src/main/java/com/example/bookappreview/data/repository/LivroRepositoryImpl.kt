package com.example.bookappreview.data.repository

import android.content.Context
import android.util.Log
import com.example.bookappreview.data.database.dao.LivroSalvoDao
import com.example.bookappreview.data.model.mapper.toEntity
import com.example.bookappreview.data.model.mapper.toLivro
import com.example.bookappreview.data.webclient.BookService
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


/**
 * Repository data - tem acesso ao banco aos Data Source
 */
class LivroRepositoryImpl @Inject constructor(
    private val livroSalvoDao: LivroSalvoDao,
    private val bookService: BookService,
    private val bookRecomendation: AiService
) : LivroRepository {


    /**
     * save books on the local db
     */
    override suspend fun saveBooks(livro: Livro) {
        livroSalvoDao.salva(livro.toEntity())
    }

    /**
     * get books from the api
     */
    override fun fetchBooks(searchQuery: String, context: Context): Flow<List<Livro>> {
        return flow {
            val books = bookService.bookApi(searchQuery, context)
            emit(books)
        }
    }

    override fun fecthLastSavedBooks(): Flow<List<Livro>> {
        return livroSalvoDao.getLatestBooks().map { entities ->
            entities.map { it.toLivro() }
        }
    }

    /**
     * Recommendation of similar books using AI
     *
     */
    override fun bookRecomendation(book: String): String {
        TODO("Not yet implemented")
    }

    override fun fetchAllBooksGroupedByDate(): Flow<Map<String, List<Livro>>> {
        return livroSalvoDao.getAllBooks().map { entities ->
            entities.map { it.toLivro() }
                .groupBy { livro ->
                    // Converte a data para "MMMM yyyy" (ex.: "Novembro 2024")
                    val isoDate = convertDateToIso(livro.dateReview)
                    val parsedDate = SimpleDateFormat("yyyy-MM", Locale.getDefault()).parse(isoDate)
                    SimpleDateFormat("MMMM yyyy", Locale("pt", "BR")).format(parsedDate!!)
                }
                .toSortedMap(compareByDescending { it }) // Ordena os meses/anos em ordem decrescente
        }
    }


    /**
     * converter a data no formato que vem do db sendo uma string
     * - dia/mes/ano para o formato de agrupamento - mes e ano
     *
     * @return uma string com o formato ano e mes
     */
    private fun convertDateToIso(date: String): String {
        return try {
            val inputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("pt", "BR"))
            val outputFormat = SimpleDateFormat("yyyy-MM", Locale.getDefault())

            val parsedDate = inputFormat.parse(date)
            if (parsedDate != null) {
                outputFormat.format(parsedDate)
            } else {
                // Retorna uma string vazia se a data não puder ser analisada
                ""
            }
        } catch (e: Exception) {
            // Log para depuração
            Log.e("convertDateToIso", "Erro ao converter a data: $date", e)
            ""
        }
    }
}

