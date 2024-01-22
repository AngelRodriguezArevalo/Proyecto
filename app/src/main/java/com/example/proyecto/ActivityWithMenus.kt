package com.example.proyecto

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class ActivityWithMenus : AppCompatActivity() {

    companion object{
        var actividadActual = 0
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        //relacionamos la clase con el layout del menu que hemos creado:
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        //desactivar la opción de la actividad en la que ya  estamos
        for (i in 0  until  menu.size()){
            if (i== actividadActual) menu.getItem(i).isEnabled = false
            else menu.getItem((i)).isEnabled = true
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.eventos -> {
                actividadActual = 0
                // hacemos que se abra la pantalla del listado de parques
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}