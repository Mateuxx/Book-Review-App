package com.example.bookappreview.helpers

import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * chama uma nova Activity
 */
fun Context.vaiPara(
    clazz: Class<*>,
//    intent: Intent.() -> Unit = {} -> PutExtra stuff
) {
    Intent(this, clazz).apply {
//        intent() -> PutExtra
        startActivity(this)
    }
}


/**
 * Função para implementar melhor o toast
 */
fun Context.toast(mensagem: String) {
    Toast.makeText( //Caso falhe a autenticação joga esse toast
        this,
        mensagem,
        Toast.LENGTH_SHORT
    ).show()
}