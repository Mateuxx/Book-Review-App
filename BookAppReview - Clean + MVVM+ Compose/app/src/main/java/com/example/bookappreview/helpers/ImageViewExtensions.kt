//package com.example.bookappreview.helpers
//
//import android.util.Log
//import android.widget.ImageView
//import coil.load
//import com.example.bookappreview.R
//
//fun ImageView.tentaCarregarImagem(
//    url: String? = null,
//    fallback: Int = R.drawable.imagem_padrao // se der errado, chama essa imagem
//) {
//    load(url) {
//        crossfade(true)
//        fallback(fallback)
//        error(R.drawable.imagem_padrao)
//        placeholder(R.drawable.placeholder)
//        listener(
//            onError = { request, throwable ->
//                /**
//                 * Erro devido ao android  o Android está bloqueando requisições HTTP sem
//                 * criptografia (ou seja, usando http:// em vez de https://). Desde o Android 9
//                 * (API 28)
//                 */
//                Log.e("CoilError", "Erro ao carregar a imagem: ${throwable.message}")
//            }
//        )
//    }
//}