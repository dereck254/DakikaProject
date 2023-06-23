package com.elon.dakikaloanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        var policy:TextView = findViewById(R.id.privacyPolicyTextView)
        policy.setOnClickListener {
            startActivity(Intent(applicationContext, PrivacyPolicyActivity::class.java))
        }
        var termsofservice:TextView = findViewById(R.id.termsofservice)
        termsofservice.setOnClickListener { startActivity(Intent(applicationContext, TermsofServiceActivity::class.java)) }

        var checkupdate:TextView = findViewById(R.id.checkforupdate)
        checkupdate.setOnClickListener { Toast.makeText(applicationContext, "This version is upto date.", Toast.LENGTH_SHORT).show() }
    }
}