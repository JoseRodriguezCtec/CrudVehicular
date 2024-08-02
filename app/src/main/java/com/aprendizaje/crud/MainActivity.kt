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

        binding.buttonsearch.setOnClickListener {
            val vehicleNumber = binding.vehicleNumber.text.toString().trim()

            if (vehicleNumber.isEmpty()) {
                Toast.makeText(this, "Ingrese un vehiculo", Toast.LENGTH_SHORT).show()
            } else {
                readData(vehicleNumber)
            }
        }
    }

    private fun readData(vehicleNumber: String) {
        databaseReference= FirebaseDatabase.getInstance().getReference("vehiculos")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            if(it.exists()){
                val uploadownerName = it.child("ownerVehiculo").value
                val uploadvehiculoBrand =it.child("vehiculoBrand").value
                val uploadvehiculoRTO = it.child("vehiculoRTO").value
                val uploadPrecio = it.child("vehiculoPrecio").value
                Toast.makeText(this, "Resultado encontrado", Toast.LENGTH_SHORT).show()
                binding.vehicleNumber.text.clear()
                binding.readOwnerName.setText(uploadownerName.toString())
                binding.readvehiculobrand.setText(uploadvehiculoBrand.toString())
                binding.readvehiculoRTO.setText(uploadvehiculoRTO.toString())
                binding.readvehiculoPrecio.setText(uploadPrecio.toString())
            }else{
                Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
            }
        }.addOnSuccessListener {
            Toast.makeText(this, "Error al buscar", Toast.LENGTH_SHORT).show()
        }

    }
}
