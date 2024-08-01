package com.aprendizaje.crud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aprendizaje.crud.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialización de Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("vehiculos")

        binding.btnBuscar.setOnClickListener {
            val busquedaVehiculo = binding.busquedaVehiculo.text.toString().trim()

            if (busquedaVehiculo.isEmpty()) {
                Toast.makeText(this, "Ingrese un vehiculo", Toast.LENGTH_SHORT).show()
            } else {
                readData(busquedaVehiculo)
            }
        }
    }

    private fun readData(busquedaVehiculo: String) {
        databaseReference= FirebaseDatabase.getInstance().getReference("vehiculos")
        databaseReference.child(busquedaVehiculo).get().addOnSuccessListener {
            if(it.exists()){
                val ownerVehiculo = it.child("ownerVehiculo").value
                val vehiculoBrand =it.child("vehiculoBrand").value
                val vehiculoRTO = it.child("vehiculoRTO").value
                val vehiculoPrecio = it.child("vehiculoPrecio").value
                Toast.makeText(this, "Resultado encontrado", Toast.LENGTH_SHORT).show()
                binding.busquedaVehiculo.text.clear()
                binding.readOwnerName.setText(ownerVehiculo.toString())
                binding.readvehiculobrand.setText(vehiculoBrand.toString())
                binding.readvehiculoRTO.setText(vehiculoRTO.toString())
                binding.readvehiculoPrecio.setText(vehiculoPrecio.toString())
            }else{
                Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
            }
        }.addOnSuccessListener {
            Toast.makeText(this, "Error al buscar", Toast.LENGTH_SHORT).show()
        }

    }
}
