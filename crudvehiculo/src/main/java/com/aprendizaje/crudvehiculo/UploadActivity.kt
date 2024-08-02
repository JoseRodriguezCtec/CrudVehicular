package com.aprendizaje.crudvehiculo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aprendizaje.crudvehiculo.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    //Se hace mención de la base de datos de Firebase y se le asigna un valor nulo
    //para que no se pueda acceder a ella sin haberla inicializado
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uploadButton.setOnClickListener{
            val ownerName = binding.uploadownerName.text.toString()
            val vehiculoBrand = binding.uploadvehiculoBrand.text.toString()
            val vehiculoRTO = binding.uploadvehiculoRTO.text.toString()
            val vehiculoNumber = binding.uploadVehiculoNumber.text.toString()
            //Se convierte el precio a un valor double para poder guardarlo en la base de datos
            val precio = binding.uploadPrecio.text.toString().toDouble()

            //Se hace llamado a la base de datos de Firebase y se le asigna el valor de la referencia
            //de la base de datos de los vehiculos para poder guardar los datos en ella
            databaseReference = FirebaseDatabase.getInstance().getReference("Vehiculos")
            val vehiculoData = VehiculoData(ownerName, vehiculoBrand, vehiculoRTO, vehiculoNumber, precio)
            databaseReference.child(vehiculoNumber).setValue(vehiculoData).addOnSuccessListener {
                binding.uploadownerName.text.clear()
                binding.uploadvehiculoBrand.text.clear()
                binding.uploadvehiculoRTO.text.clear()
                binding.uploadVehiculoNumber.text.clear()
                binding.uploadPrecio.text.clear()

                Toast.makeText(this, "Vehiculo registrado", Toast.LENGTH_SHORT).show()
                val intent = Intent (this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Error al registrar el vehiculo", Toast.LENGTH_SHORT).show()
            }
        }

    }
}