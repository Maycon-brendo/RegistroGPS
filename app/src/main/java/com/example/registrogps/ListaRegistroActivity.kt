package com.example.registrogps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.registrogps.adapters.AdapterRegistros
import com.example.registrogps.databinding.ActivityListaRegistroBinding
import com.example.registrogps.models.Registro


class ListaRegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaRegistroBinding
    private lateinit var adapterRegistros: AdapterRegistros
    private val listadeRegistros: MutableList<Registro> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaRegistroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerViewRegistros = binding.rvRegistros
        recyclerViewRegistros.layoutManager = LinearLayoutManager(this)
        recyclerViewRegistros.setHasFixedSize(true)
        adapterRegistros = AdapterRegistros(this, listadeRegistros)
        recyclerViewRegistros.adapter = adapterRegistros
        setup()
    }


    private fun setup() {
        listFiles()
        setuupClickListener()
    }

    private fun setuupClickListener() {
        binding.btnListar.setOnClickListener{
            listFiles2()
        }
    }

    private fun listFiles() {
        val files: Array<String> = fileList()

        val texto = ""

        files.forEach {
            val tito1 = Registro("${texto}${it}\n")
            listadeRegistros.add(tito1)
        }

    }

    fun listFiles2(){
        var files: Array<String> = fileList()

        var  texto = ""
        files.forEach {
            texto = "${texto}${it}\n"
        }

        binding.tvopcao2.text = texto

    }
}