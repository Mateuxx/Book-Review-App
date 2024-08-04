package com.example.bookappreview.repository.helpers

import android.app.Application
import com.example.bookappreview.dao.UsuarioDao
import com.example.bookappreview.repository.UsuarioRepository

/**
 * Nao consguimos encapsular diretamente, por isso o helper para criar esse encapsulamento para nao
 * ser na Main ACtivity
 */
class EncapsulamentoMVVM: Application() {
    private val usuarioDao by lazy { UsuarioDao() }
    val usuarioRepository: UsuarioRepository by lazy { UsuarioRepository(usuarioDao) }
}