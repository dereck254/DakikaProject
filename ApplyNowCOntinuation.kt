package com.elon.dakikaloanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.net.Uri
import android.app.Activity.RESULT_OK
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class ApplyNowCOntinuation : AppCompatActivity() {
    private lateinit var maritalStatusSpinner: Spinner
    private lateinit var occupationStatusSpinner: Spinner
    private lateinit var residentialStatusSpinner: Spinner
    private lateinit var firstEmergencyContactEditText: EditText
    private lateinit var secondEmergencyContactEditText: EditText
    private lateinit var paydaySpinner: Spinner
    private lateinit var personalEmailEditText: EditText
    private lateinit var submitButton: Button

    private val PICK_CONTACT_REQUEST = 1
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.apply_now_continuation)
        maritalStatusSpinner = findViewById(R.id.maritalspinner)
        occupationStatusSpinner = findViewById(R.id.occupationspinner)
        residentialStatusSpinner = findViewById(R.id.residential)
        firstEmergencyContactEditText = findViewById(R.id.firstcontactSpinner)
        secondEmergencyContactEditText = findViewById(R.id.secondcontactSpinner)
        paydaySpinner = findViewById(R.id.paydaySpinner)
        personalEmailEditText = findViewById(R.id.emailaddress)
        submitButton = findViewById(R.id.submit_button)
        val back: Button = findViewById(R.id.backbutton)
        back.setBackgroundColor(Color.GREEN)
        submitButton.setOnClickListener {
            saveDataToFirebase()
        }
        firstEmergencyContactEditText.setOnClickListener {
            openContactsApp(PICK_CONTACT_REQUEST)
        }
        secondEmergencyContactEditText.setOnClickListener {
            openContactsApp(PICK_CONTACT_REQUEST + 1)
        }
        back.setOnClickListener {
            navigateToApplyNowActivity()
        }
        val maritalOptions: Array<String> = resources.getStringArray(R.array.marital_options)
        val maritalStatusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, maritalOptions)
        maritalStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        maritalStatusSpinner.adapter = maritalStatusAdapter

        val employmentOptions: Array<String> = resources.getStringArray(R.array.employment_options)
        val occupationStatusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, employmentOptions)
        occupationStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        occupationStatusSpinner.adapter = occupationStatusAdapter

        val days = (1..31).toList()
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        paydaySpinner.adapter = dayAdapter
    }

    private fun saveDataToFirebase() {
        // Retrieve values from spinners and edit texts
        val maritalStatus = maritalStatusSpinner.selectedItem.toString()
        val occupationStatus = occupationStatusSpinner.selectedItem.toString()
        val residentialStatus = residentialStatusSpinner.selectedItem.toString().trim()
        val firstEmergencyContact = firstEmergencyContactEditText.text.toString().trim()
        val secondEmergencyContact = secondEmergencyContactEditText.text.toString().trim()
        val payday = paydaySpinner.selectedItem.toString().trim()
        val personalEmail = personalEmailEditText.text.toString().trim()

        // Perform data validation if required

        val data = hashMapOf(
            "maritalStatus" to maritalStatus,
            "occupationStatus" to occupationStatus,
            "residentialStatus" to residentialStatus,
            "firstEmergencyContact" to firstEmergencyContact,
            "secondEmergencyContact" to secondEmergencyContact,
            "payday" to payday,
            "personalEmail" to personalEmail
        )

        firestore.collection("loanApplications")
            .add(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MyLoanActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
            }
    }

    private fun navigateToApplyNowActivity() {
        val intent = Intent(this, ApplyNowActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openContactsApp(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_CONTACT_REQUEST || requestCode == PICK_CONTACT_REQUEST + 1) {
                val contactUri = data?.data
                val contactDetails = retrieveContactDetails(contactUri)
                val contactName = contactDetails.first
                val contactPhoneNumber = contactDetails.second

                if (requestCode == PICK_CONTACT_REQUEST) {
                    firstEmergencyContactEditText.setText(contactName)
                    firstEmergencyContactEditText.setSelection(contactName.length)
                } else if (requestCode == PICK_CONTACT_REQUEST + 1) {
                    secondEmergencyContactEditText.setText(contactName)
                    secondEmergencyContactEditText.setSelection(contactName.length)
                }
            }
        }
    }

    private fun retrieveContactDetails(contactUri: Uri?): Pair<String, String> {
        var contactName = ""
        var contactPhoneNumber = ""

        val cursor = contentResolver.query(contactUri!!, null, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {
                val contactNameIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                if (contactNameIndex != -1) {
                    contactName = it.getString(contactNameIndex)
                }

                val contactIdIndex = it.getColumnIndex(ContactsContract.Contacts._ID)
                if (contactIdIndex != -1) {
                    val contactId = it.getString(contactIdIndex)

                    val phoneCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(contactId),
                        null
                    )

                    phoneCursor?.use { phoneCursor ->
                        if (phoneCursor.moveToFirst()) {
                            val phoneNumberIndex =
                                phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            if (phoneNumberIndex != -1) {
                                contactPhoneNumber = phoneCursor.getString(phoneNumberIndex)
                            }
                        }
                    }
                }
            }
        }

        return Pair(contactName, contactPhoneNumber)
    }

    override fun onBackPressed() {
        // Redirect to the HomeActivity
        navigateToApplyNowActivity()
    }
}
