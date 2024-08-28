package com.example.bookappreview.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappreview.databinding.ActivityLivroDetalhesBinding
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.model.Livro

class LivroDetalhesActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityLivroDetalhesBinding.inflate(layoutInflater)
    }

    private var livroCarregado: Livro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        livroCarregado = carregaLivro()
        Log.i("TAG", "onCreate: livro carregador: $livroCarregado")
        preencherInfos(livroCarregado!!)
    }

    override fun onResume() {
        super.onResume()
        binding.threeDots.setOnClickListener {
            val intent = Intent(
                this,
                ReviewLivroActivity::class.java
            ).apply {
                Log.i("TAG", "LivroDetalhesactivity: livro ID: $livroCarregado")
                putExtra(CHAVE_LIVRO_OBj, livroCarregado)
            }
            startActivity(intent)//inicia a activty recebendo a intent no qual foi passada
        }

    }


    private fun carregaLivro(): Livro {
        //Refatorar depois
        val livro = intent.getParcelableExtra<Livro>("LIVRO_OBJ")
        Log.i("TAG", "carregaLivro: ${livro?.description}")
        return livro!!
    }

    private fun preencherInfos(livro: Livro) {

        var isExpanded = false

        binding.apply {
            titulo.text = livro.title.toString()
            autor.text = livro.autor.toString()
            genero.text = livro.genero
            Log.i("TAG", "preencherInfos: Genero: ${livro.genero}")
            pages.text = livro.pageCount.toString()
            descricaoTexto.text = livro.description.toString()
            descricaoTexto.setOnClickListener {
                if (isExpanded) {
                    descricaoTexto.maxLines = 5
                    descricaoTexto.ellipsize = TextUtils.TruncateAt.END
                    isExpanded = false
                } else {
                    descricaoTexto.maxLines = Int.MAX_VALUE
                    descricaoTexto.ellipsize = null
                    isExpanded = true
                }

            }
            imagemLivro.tentaCarregarImagem(livro.imagem)
        }
    }
}
