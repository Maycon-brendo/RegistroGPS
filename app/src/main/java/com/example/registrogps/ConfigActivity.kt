package com.example.registrogps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registrogps.databinding.ActivityConfigBinding
import com.example.registrogps.databinding.ActivityMainBinding
import com.example.registrogps.utils.getLoginFromSharedPrefs
import com.example.registrogps.utils.saveLoginToSharedPrefs

class ConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConfigBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }


    private fun setup() {
        setupClickListeners()
        setinputyourname()
    }

    private fun setinputyourname() {
        binding.tvNome.text = getLoginFromSharedPrefs()
    }

    private fun setupClickListeners() {
        binding.apply {
            btnSalvaconfig.setOnClickListener {
                val login = binding.inputNome.text.toString()
                saveLoginToSharedPrefs(login)
                setinputyourname()
            }
        }
    }
}