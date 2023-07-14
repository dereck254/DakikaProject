package com.elon.dakikaloanapp

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.view.Gravity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.util.Patterns
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ApplyNowActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var bar:EditText
    lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var educationSpinner: Spinner
    private lateinit var monthlyIncomeSpinner: Spinner
    private lateinit var outstandingLoanSpinner: Spinner
    private lateinit var dayOfBirthSpinner: Spinner
    private lateinit var monthOfBirthSpinner: Spinner
    private lateinit var yearOfBirthSpinner: Spinner
    private lateinit var idNumberEditText: EditText

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userRef: DatabaseReference

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var progress: ProgressDialog

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_now)
        progress = ProgressDialog(this)
        toolbar = findViewById(R.id.toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        firstNameEditText = findViewById(R.id.first_name_edittext)
        lastNameEditText = findViewById(R.id.last_name_edittext)
        genderSpinner = findViewById(R.id.genderSpinner)
        educationSpinner = findViewById(R.id.educationSpinner)
        monthlyIncomeSpinner = findViewById(R.id.monthlyincomeSpinner)
        outstandingLoanSpinner = findViewById(R.id.outstandingloanSpinner)
        dayOfBirthSpinner = findViewById(R.id.daySpinner)
        monthOfBirthSpinner = findViewById(R.id.monthSpinner)
        yearOfBirthSpinner = findViewById(R.id.yearSpinner)
        idNumberEditText = findViewById(R.id.id_number)

        val nextButton:Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener { onNextButtonClick() }


        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        userRef = database.reference.child("users")
        var next: Button = findViewById(R.id.next_button)
        next.setBackgroundColor(Color.GREEN)



        // Set the toolbar as the action bar
        setSupportActionBar(toolbar)

        // Set up the navigation drawer
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle navigation menu item clicks
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item2 -> {
                    // Handle item 2 click
                }

                R.id.nav_item3 -> {
                    // Handle item 3 click
                }

                R.id.nav_item4 -> {
                    startActivity(Intent(this, PayLoanActivity::class.java))
                    // Handle item 4 click
                }

                R.id.nav_item5 -> {
                    // Handle item 5 click
                }

                R.id.nav_item6 -> {
                    // Handle item 6 click
                    startActivity(Intent(applicationContext, AboutActivity::class.java))
                }

                R.id.nav_item7 -> {
                    // Handle item 7 (logout) click
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Sure to Log Out")
                    alertDialog.setMessage("Log out?")
                    alertDialog.setNegativeButton("No", null)
                    alertDialog.setPositiveButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialog, which ->
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        })
                    alertDialog.create().show()
                    finish()
                }
            }
            true
        }

        val genderSpinner: Spinner = findViewById(R.id.genderSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.gender_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genderSpinner.adapter = adapter
        }

        val educationSpinner: Spinner = findViewById(R.id.educationSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.education_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            educationSpinner.adapter = adapter
        }

        val incomeSpinner: Spinner = findViewById(R.id.monthlyincomeSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.monthly_income,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            incomeSpinner.adapter = adapter
        }

        val outstandingloanSpinner: Spinner = findViewById(R.id.outstandingloanSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.outstanding_loan,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            outstandingloanSpinner.adapter = adapter
        }

        val daySpinner: Spinner = findViewById(R.id.daySpinner)
        val monthSpinner: Spinner = findViewById(R.id.monthSpinner)
        val yearSpinner: Spinner = findViewById(R.id.yearSpinner)
        val days = (1..31).toList()
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySpinner.adapter = dayAdapter

        // Populate month spinner
        val months = arrayOf(
            "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"
        )
        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = monthAdapter

        // Populate year spinner
        val years = (1970..2023).toList() // Adjust the range as per your requirement
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = yearAdapter
    }
    private fun onNextButtonClick() {
        val firstName = firstNameEditText.text.toString().trim()
        val lastName = lastNameEditText.text.toString().trim()
        val gender = genderSpinner.selectedItem.toString()
        val educationLevel = educationSpinner.selectedItem.toString()
        val monthlyIncome = monthlyIncomeSpinner.selectedItem.toString()
        val hasOutstandingLoan = outstandingLoanSpinner.selectedItem.toString()
        val dayOfBirth = dayOfBirthSpinner.selectedItem.toString()
        val monthOfBirth = monthOfBirthSpinner.selectedItem.toString()
        val yearOfBirth = yearOfBirthSpinner.selectedItem.toString()
        val idNumber = idNumberEditText.text.toString().trim()

        if (validateInput(firstName, lastName, idNumber)) {
            saveDataToFirebase(firstName, lastName, gender, educationLevel, monthlyIncome, hasOutstandingLoan, dayOfBirth, monthOfBirth, yearOfBirth, idNumber)
            val intent = Intent(this, MyLoanActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            // Proceed to ApplyNowContinuationActivity
        }
    }
    private fun validateInput(firstName: String, lastName: String, idNumber: String): Boolean {
    if (firstName.isEmpty() || lastName.isEmpty() || idNumber.isEmpty()) {
        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        return false
    }
    if (!Patterns.PHONE.matcher(idNumber).matches()) {
        idNumberEditText.error = "Invalid ID number"
        return false
    }
    // Perform additional validation as needed
    return true
}

private fun saveDataToFirebase(firstName: String, lastName: String, gender: String, educationLevel: String, monthlyIncome: String, hasOutstandingLoan: String, dayOfBirth: String, monthOfBirth: String, yearOfBirth: String, idNumber: String) {
    val currentUser: FirebaseUser? = firebaseAuth.currentUser
    val userId: String? = currentUser?.uid

    if (userId != null) {
        val userData = HashMap<String, Any>()
        userData["firstName"] = firstName
        userData["lastName"] = lastName
        userData["gender"] = gender
        userData["educationLevel"] = educationLevel
        userData["monthlyIncome"] = monthlyIncome
        userData["hasOutstandingLoan"] = hasOutstandingLoan
        userData["dayOfBirth"] = dayOfBirth
        userData["monthOfBirth"] = monthOfBirth
        userData["yearOfBirth"] = yearOfBirth
        userData["idNumber"] = idNumber

        userRef.child(userId).updateChildren(userData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
    override fun onBackPressed() {
        // Redirect to the HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}

