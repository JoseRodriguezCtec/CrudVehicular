package com.aprendizaje.crudvehiculo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aprendizaje.crudvehiculo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Binding se usa para los vinculos de los elementos de la vista con el codigo de la actividad o fragmento
        //por eso se debe de inflar el layout de la vista con el metodo inflate de la clase ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        //binding.root es el contenedor de la vista que se infla con el metodo inflate de la clase ActivityMainBinding
        //también se usa para tener acceso a los elementos de la vista
        setContentView(binding.root)

        //Se le hace llamado al boton del mainActivity y se le asigna un evento
        binding.btnRegistrarVehiculo.setOnClickListener {
            //Se crea un intent para pasar de una actividad a otra y
            //se le pasa el contexto de la actividad actual y la actividad a la que se quiere ir
            val intent = Intent(this@MainActivity, Upload::class.java)
            startActivity(intent)
            finish()

        }

    }
}