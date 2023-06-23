package com.elon.dakikaloanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore





class ApplyNowCOntinuation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.apply_now_continuation)
        val firestore = FirebaseFirestore.getInstance()

        val back:Button = findViewById(R.id.backbutton)
        back.setBackgroundColor(Color.GREEN)
        back.setOnClickListener { startActivity(Intent(applicationContext, ApplyNowActivity::class.java)) }
        val maritalstatus: Spinner = findViewById(R.id.maritalspinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.marital_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            maritalstatus.adapter = adapter
        }
        val occupation: Spinner = findViewById(R.id.occupationspinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.employment_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            occupation.adapter = adapter
        }
        val paydaySpinner: Spinner = findViewById(R.id.paydaySpinner)
        val days = (1..31).toList()
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        paydaySpinner.adapter = dayAdapter
    }
}