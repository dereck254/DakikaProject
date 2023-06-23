package com.elon.dakikaloanapp

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.os.Bundle
import android.widget.TextView
import android.graphics.Color
import android.content.Intent
import android.view.Gravity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var permissionsList: MutableList<String>
    private var currentPermissionIndex = 0

    private lateinit var regtext: TextView
    private lateinit var loginviaemail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        regtext = findViewById(R.id.mtvreg)
        loginviaemail = findViewById(R.id.loginviaemailbtn)

        regtext.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
        loginviaemail.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        permissionsList = mutableListOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_SMS,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.SEND_SMS
        )

        requestNextPermission()
    }

    private fun requestNextPermission() {
        if (permissionsList.isNotEmpty()) {
            val permission = permissionsList.removeAt(0)
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), currentPermissionIndex)
            } else {
                currentPermissionIndex++
                requestNextPermission()
            }
        } else {
            // All permissions granted, proceed to next page
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode >= 0 && requestCode < permissions.size) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                currentPermissionIndex++
                requestNextPermission()
            } else {
                showPermissionDeniedDialog()
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("You have denied the required permission. This app cannot proceed without it. Please grant the permission from the device settings.")
            .setPositiveButton("OK") { _: DialogInterface, _: Int ->
                finish() // Close the app or handle accordingly
            }
            .setCancelable(false)
            .show()
    }
}

