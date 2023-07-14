package com.elon.dakikaloanapp

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class MyProfileActivity : AppCompatActivity() {

    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var educationLevelTextView: TextView
    private lateinit var monthlyIncomeTextView: TextView
    private lateinit var hasOutstandingLoanTextView: TextView
    private lateinit var dateOfBirthTextView: TextView
    private lateinit var idNumberTextView: TextView

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userRef: DatabaseReference
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        firstNameTextView = findViewById(R.id.first_name_textview)
        lastNameTextView = findViewById(R.id.last_name_textview)
        genderTextView = findViewById(R.id.gender_textview)
        educationLevelTextView = findViewById(R.id.education_level_textview)
        monthlyIncomeTextView = findViewById(R.id.monthly_income_textview)
        hasOutstandingLoanTextView = findViewById(R.id.has_outstanding_loan_textview)
        dateOfBirthTextView = findViewById(R.id.date_of_birth_textview)
        idNumberTextView = findViewById(R.id.id_number_textview)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        userRef = database.reference.child("users")
        firestore = FirebaseFirestore.getInstance()

        val currentUser = firebaseAuth.currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            userRef.child(userId).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val firstName = snapshot.child("firstName").getValue(String::class.java)
                    val lastName = snapshot.child("lastName").getValue(String::class.java)
                    val gender = snapshot.child("gender").getValue(String::class.java)
                    val educationLevel = snapshot.child("educationLevel").getValue(String::class.java)
                    val monthlyIncome = snapshot.child("monthlyIncome").getValue(String::class.java)
                    val hasOutstandingLoan = snapshot.child("hasOutstandingLoan").getValue(String::class.java)
                    val dayOfBirth = snapshot.child("dayOfBirth").getValue(String::class.java)
                    val monthOfBirth = snapshot.child("monthOfBirth").getValue(String::class.java)
                    val yearOfBirth = snapshot.child("yearOfBirth").getValue(String::class.java)
                    val idNumber = snapshot.child("idNumber").getValue(String::class.java)

                    firstNameTextView.text = firstName
                    lastNameTextView.text = lastName
                    genderTextView.text = gender
                    educationLevelTextView.text = educationLevel
                    monthlyIncomeTextView.text = monthlyIncome
                    hasOutstandingLoanTextView.text = hasOutstandingLoan
                    dateOfBirthTextView.text = "$dayOfBirth $monthOfBirth, $yearOfBirth"
                    idNumberTextView.text = idNumber
                }
            }
        }
        // Retrieve data from Firebase Firestore (ApplyNowContinuation activity)
        val loanApplicationRef = firestore.collection("loanApplications")
        loanApplicationRef.get().addOnSuccessListener { snapshot ->
            for (document in snapshot) {
                val maritalStatus = document.getString("maritalStatus")
                val occupationStatus = document.getString("occupationStatus")
                val residentialStatus = document.getString("residentialStatus")
                val firstEmergencyContact = document.getString("firstEmergencyContact")
                val secondEmergencyContact = document.getString("secondEmergencyEmergencyContact")
                val payday = document.getString("payday")
                val personalEmail = document.getString("personalEmail")

                // Display the data in the MyProfileActivity
                val maritalStatusTextView = TextView(this)
                maritalStatusTextView.text = "Marital Status: $maritalStatus"

                val occupationStatusTextView = TextView(this)
                occupationStatusTextView.text = "Occupation Status: $occupationStatus"

                val residentialStatusTextView = TextView(this)
                residentialStatusTextView.text = "Residential Status: $residentialStatus"

                val firstEmergencyContactTextView = TextView(this)
                firstEmergencyContactTextView.text = "First Emergency Contact: $firstEmergencyContact"

                val secondEmergencyContactTextView = TextView(this)
                secondEmergencyContactTextView.text = "Second Emergency Contact: $secondEmergencyContact"

                val paydayTextView = TextView(this)
                paydayTextView.text = "Payday: $payday"

                val personalEmailTextView = TextView(this)
                personalEmailTextView.text = "Personal Email: $personalEmail"

                val linearLayout = findViewById<LinearLayout>(R.id.linear_layout)
                linearLayout.addView(maritalStatusTextView)
                linearLayout.addView(occupationStatusTextView)
                linearLayout.addView(residentialStatusTextView)
                linearLayout.addView(firstEmergencyContactTextView)
                linearLayout.addView(secondEmergencyContactTextView)
                linearLayout.addView(paydayTextView)
                linearLayout.addView(personalEmailTextView)
            }
        }
    }

    override fun onBackPressed() {
        // Redirect to the HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
