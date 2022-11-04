package com.example.registrogps.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registrogps.databinding.ListaItemRegistroBinding
import com.example.registrogps.models.Registro

class AdapterRegistros (private val context: Context, private val listadeRegistros: MutableList<Registro>):
    RecyclerView.Adapter<AdapterRegistros.RegistroViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val itemLista  = ListaItemRegistroBinding.inflate(LayoutInflater.from(context), parent, false)
        return RegistroViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        holder.tvNomeArquivo.text = listadeRegistros[position].titulo
    }

    override fun getItemCount() = listadeRegistros.size

    inner class RegistroViewHolder(binding: ListaItemRegistroBinding): RecyclerView.ViewHolder(binding.root) {
        val tvNomeArquivo = binding.tvNomeArquivo

    }
}