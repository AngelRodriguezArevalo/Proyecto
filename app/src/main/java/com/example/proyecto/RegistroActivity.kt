package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        binding.buttonRegistrarRegistro.setOnClickListener {

            //comprobar que ningún campo está vacío
            if(binding.editTextNombreRegistro.text.isNotEmpty() && binding.editTextPaswordRegistro.text.isNotEmpty()
                && binding.editTextApellidosRegistro.text.isNotEmpty() && binding.editTextEmailRegistro.text.isNotEmpty()){

                //acceder a firebase con el método createUser... y le pasamos el correo y el password
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.editTextEmailRegistro.text.toString(), binding.editTextPaswordRegistro.text.toString()
                )
                    .addOnCompleteListener{
                        //si el usuario se ha registrado correctamente muestra la pantalla de bienvenida
                        if(it.isSuccessful){

                            // registrar en la base de datos los datos del usuario

                            db.collection("usuarios").document(binding.editTextEmailRegistro.text.toString())
                                .set(mapOf(
                                    "nombre" to binding.editTextNombreRegistro.text.toString(),
                                    "apellidos" to binding.editTextApellidosRegistro.text.toString()
                                ))

                            Toast.makeText(this, "Se han registrado los datos con éxito", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                        }
                        //si no, nos avisa del error
                        else{Toast.makeText(this, "No se han podido registrar los datos", Toast.LENGTH_SHORT).show()}
                    }

            }else{Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()}
        }
    }
}