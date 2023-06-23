package com.elon.dakikaloanapp

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.*
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MyProfileActivity : AppCompatActivity() {


    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        firestore = FirebaseFirestore.getInstance()

        val saveButton: Button = findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            updateDataInFirestore()
        }

        retrieveDataFromFirestore()
    }

    private fun retrieveDataFromFirestore() {
        val documentRef = firestore.collection("users").document("user_id") // Replace "user_id" with the actual user ID

        documentRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val firstName = documentSnapshot.getString("firstName")
                    val lastName = documentSnapshot.getString("lastName")
                    val gender = documentSnapshot.getString("gender")

                    // Update the UI with the retrieved data
                    val firstNameEditText: EditText = findViewById(R.id.first_name_edittext)
                    val lastNameEditText: EditText = findViewById(R.id.last_name_edittext)
                    val genderSpinner: Spinner = findViewById(R.id.genderSpinner)

                    firstNameEditText.setText(firstName)
                    lastNameEditText.setText(lastName)

                    val genderAdapter = genderSpinner.adapter as ArrayAdapter<String>
                    val genderPosition = genderAdapter.getPosition(gender)
                    genderSpinner.setSelection(genderPosition)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to retrieve data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateDataInFirestore() {
        val firstNameEditText: EditText = findViewById(R.id.first_name_edittext)
        val lastNameEditText: EditText = findViewById(R.id.last_name_edittext)
        val genderSpinner: Spinner = findViewById(R.id.genderSpinner)

        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val gender = genderSpinner.selectedItem.toString()

        val documentRef = firestore.collection("users").document("user_id") // Replace "user_id" with the actual user ID

        val userData = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "gender" to gender
            // ... Add other data to the map
        )

        documentRef.set(userData)
            .addOnSuccessListener {
                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to update data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}