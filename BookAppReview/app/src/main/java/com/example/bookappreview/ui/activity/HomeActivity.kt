package com.example.bookappreview.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookappreview.R
import com.example.bookappreview.databinding.ActivityHomeBinding
import com.example.bookappreview.helpers.vaiPara

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val btn = binding.button
        btn.setOnClickListener {
            vaiPara(AddLivroActivity::class.java)
        }
    }
}