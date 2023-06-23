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

class ApplyNowActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var bar:EditText
    lateinit var firestore: FirebaseFirestore

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

        firestore = FirebaseFirestore.getInstance()
        var next: Button = findViewById(R.id.next_button)
        next.setBackgroundColor(Color.GREEN)

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            saveDataToFirestore()
        }


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
        val years = (1950..2023).toList() // Adjust the range as per your requirement
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = yearAdapter
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime > 2000) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        } else {
            super.onBackPressed()
        }
    }

    private fun saveDataToFirestore() {
        val firstNameEditText: EditText = findViewById(R.id.first_name_edittext)
        val lastNameEditText: EditText = findViewById(R.id.last_name_edittext)
        val genderSpinner: Spinner = findViewById(R.id.genderSpinner)
        // ... Retrieve other views and data here

        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val gender = genderSpinner.selectedItem.toString()
        // ... Retrieve other data here

        // Create a new document with a generated ID
        val documentRef = firestore.collection("users").document()

        // Create a map to store the data
        val userData = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "gender" to gender
            // ... Add other data to the map
        )

        // Save the data to Firestore
        documentRef.set(userData)
            .addOnSuccessListener {
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to save data: ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }
}

