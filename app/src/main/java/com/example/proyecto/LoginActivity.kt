package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.vectordrawable.animated.R
import com.example.proyecto.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoginLogin.setOnClickListener {
            login()
        }

        binding.buttonRegistrarLogin.setOnClickListener {
            registro()
        }
    }

    // EVENTO AL PULSAR EL BOTÓN REGISTRAR
    private fun registro() {
        val intent = Intent(this, CrearEventoActivity::class.java)
        startActivity(intent)
    }

    // EVENTO AL PULSAR EL BOTÓN INICIAR SESIÓN
    private fun login() {

        if (binding.editTextEmailLogin.text.isNotEmpty() && binding.editTextPasswordLogin.text.isNotEmpty()) {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.editTextEmailLogin.text.toString(),
                binding.editTextPasswordLogin.text.toString()
            )

                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Correo o contraseña incorrectos. Revise los datos introducidos.", Toast.LENGTH_LONG).show()
                    }
                }

        } else {
            Toast.makeText(this, "Algún campo está vacío. Por favor, ingrese todos los datos necesarios.", Toast.LENGTH_SHORT).show()
        }
    }

}