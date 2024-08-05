package com.example.bookappreview.helpers

import android.content.Context
import android.content.Intent

/**
 * chama uma nova Activity
 */
fun Context.vaiPara(
    clazz: Class<*>,
    intent: Intent.() -> Unit = {}
) {
    Intent(this, clazz).apply {
        intent()
        startActivity(this)
    }
}