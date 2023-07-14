package com.elon.dakikaloanapp

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import android.widget.TextView
import android.graphics.Color
import android.content.Intent
import android.view.Gravity
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var permissionsList: MutableList<String>
    private var currentPermissionIndex = 0
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var regtext: TextView
    private lateinit var loginviaemail: Button
    private lateinit var loginViaGoogleButton: Button

    companion object {
        private const val RC_GOOGLE_SIGN_IN = 123
    }

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        regtext = findViewById(R.id.mtvreg)
        loginviaemail = findViewById(R.id.loginviaemailbtn)
        loginViaGoogleButton = findViewById(R.id.loginviagooglebtn)
        loginViaGoogleButton.setBackgroundColor(Color.YELLOW)

        regtext.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        loginviaemail.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        loginViaGoogleButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
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

        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    private fun requestNextPermission() {
        if (permissionsList.isNotEmpty()) {
            val permission = permissionsList.removeAt(0)
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), currentPermissionIndex)
            } else {
                currentPermissionIndex++
                requestNextPermission()
            }
        } else {
            // All permissions granted, proceed to next page
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, you can now use the account details
            // For example, you can get the user's ID token and authenticate with Firebase

            val idToken = account?.idToken
            if (idToken != null) {
                firebaseAuth = FirebaseAuth.getInstance()

                // Authenticate the user with Firebase using the Google ID token
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign-in successful, navigate to home page
                            navigateToHomeActivity()
                        } else {
                            // Sign-in failed, handle the error
                            Snackbar.make(
                                findViewById(android.R.id.content),
                                "Google Sign-In failed: ${task.exception?.message}",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

        } catch (e: ApiException) {
            // Sign in failed, handle the error
            Snackbar.make(
                findViewById(android.R.id.content),
                "Google Sign-In failed: ${e.localizedMessage}",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
