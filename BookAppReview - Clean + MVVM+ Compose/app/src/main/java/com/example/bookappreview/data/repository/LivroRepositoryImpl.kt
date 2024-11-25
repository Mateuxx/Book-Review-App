package com.example.bookappreview.data.repository

import android.content.Context
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


/**
 * Repository data - tem acesso ao banco aos Data Source
 */
class LivroRepositoryImpl(
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
     */
    override fun bookRecomendation(book: String): String {
        TODO("Not yet implemented")
    }

    override fun fetchAllBooksGroupedByDate(): Flow<Map<String, List<Livro>>> {
        return livroSalvoDao.getAllBooks().map { entities ->
            entities.map { it.toLivro() }
                .groupBy { livro ->
                    //converter a data para o formato de agrupamento para mes e ano
                    val isoDate = convertDateToIso(livro.dateReview)
                    val parsedDate =
                        //pega a data convertida e passa para o formato date
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(isoDate)
                    // formata a data para o formato desejado - janeiro 2024
                    SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(parsedDate!!)
                }
                .toSortedMap(compareByDescending { it })
        }
    }

    /**
     * converter a data no formato que vem do db sendo uma string
     * - dia/mes/ano para o formato de agrupamento - mes e ano
     *
     * @return uma string com o formato ano e mes
     */
    private fun convertDateToIso(date: String): String {
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM", Locale.getDefault())
        val parseDate = inputFormat.parse(date)
        return if (parseDate != null) {
            outputFormat.format(parseDate)
        } else {
            ""
        }
    }
}

