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
        databaseReference.child(busquedaVehiculo).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val ownerVehiculo = dataSnapshot.child("OwnerVehiculo").value
                val vehiculoBrand = dataSnapshot.child("vehiculoBrand").value
                val vehiculoRTO = dataSnapshot.child("vehiculoRTO").value
                val vehiculoPrecio = dataSnapshot.child("vehiculoPrecio").value

                Toast.makeText(this@MainActivity, "Resultado encontrado", Toast.LENGTH_SHORT).show()

                binding.busquedaVehiculo.text.clear()
                binding.readOwnerName.text = ownerVehiculo.toString()
                binding.readvehiculobrand.text = vehiculoBrand.toString()
                binding.readvehiculoRTO.text = vehiculoRTO.toString()
                binding.readvehiculoPrecio.text = vehiculoPrecio.toString()
            } else {
                Toast.makeText(this@MainActivity, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this@MainActivity, "Error al obtener datos: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
