package com.aprendizaje.crudvehiculo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aprendizaje.crudvehiculo.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Update : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Se obtiene el valor del vehiculo a actualizar
        binding.updateButton.setOnClickListener {
            val RefVehiculoNumber = binding.referenceVehicleNumber.text.toString()
            val RefownerName = binding.referenceOwnerName.text.toString()
            val RefvehicleBrand = binding.referenceVehicleBrand.text.toString()
            val RefvehicleRTO = binding.referenceUpdateRTO.text.toString()
            val Refprecio = binding.referenceUpdatePrecio.text.toString().toDouble()

            //Se valida que los campos no esten vacios
            updateData(RefVehiculoNumber, RefownerName, RefvehicleBrand, RefvehicleRTO, Refprecio)

        }

    }

    //Se crea una funcion para actualizar los datos de un vehiculo
    private fun updateData(VehiculoNumber:String, ownerName:String, vehicleBrand:String, vehicleRTO:String,precio:Double){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehiculos")
        val vehiculoData = mapOf<String, String>(
            "OwnerName" to ownerName,
            "VehiculoBrand" to vehicleBrand,
            "VehiculoRTO" to vehicleRTO,
            "VehiculoNumber" to VehiculoNumber,
            "Precio" to precio.toString()
        )
        databaseReference.child(VehiculoNumber).updateChildren(vehiculoData).addOnSuccessListener {
            binding.referenceOwnerName.text.clear()
            binding.referenceVehicleBrand.text.clear()
            binding.referenceUpdateRTO.text.clear()
            binding.referenceVehicleNumber.text.clear()
            binding.referenceUpdatePrecio.text.clear()
            Toast.makeText(this, "Vehiculo actualizado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error al actualizar el vehiculo", Toast.LENGTH_SHORT).show()
        }
    }

}