package com.aprendizaje.crudvehiculo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aprendizaje.crudvehiculo.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.DeleteButton.setOnClickListener {
            val VehiculoNumber = binding.VehicleDelete.text.toString()
            if (VehiculoNumber.isEmpty()) {
               deleteData(VehiculoNumber)
            }else{
                Toast.makeText(this, "Please Enter Vehicle Number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(VehiculoNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("VehiculoData")
        databaseReference.child(VehiculoNumber).removeValue().addOnSuccessListener {
            binding.VehicleDelete.text.clear()
            Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener {
            Toast.makeText(this, "Data Not Deleted", Toast.LENGTH_SHORT).show()
        }
    }
}