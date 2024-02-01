package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.Adapter.EventAdapter
import com.example.proyecto.databinding.ActivityMainBinding

class MainActivity : ActivityWithMenus() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        var adapter = EventAdapter(EventProvider.eventList)
        binding.recycler.adapter = adapter

        binding.filtro.addTextChangedListener {filtro ->
            val filtroEvento = EventProvider.eventList.filter { event ->
                event.nombre.lowercase().contains(filtro.toString().lowercase())
            }
            adapter.actualizarEventos(filtroEvento)
        }
    }
}