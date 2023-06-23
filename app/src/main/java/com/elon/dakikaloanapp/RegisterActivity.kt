package com.elon.dakikaloanapp

import android.app.ProgressDialog
import android.text.InputType
import android.widget.CheckBox
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {

    lateinit var edtemail:EditText
    lateinit var edtpwd:EditText
    lateinit var btnreg:Button
    lateinit var tvlogin:TextView
    lateinit var idnum:TextView
    lateinit var confirmpwd: EditText
    lateinit var progress:ProgressDialog
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        edtemail=findViewById(R.id.editTextTextEmailAddress)
        edtpwd=findViewById(R.id.editTextTextPassword)
        btnreg=findViewById(R.id.buttonlogin)
        tvlogin=findViewById(R.id.mTvreg)
        val showPasswordCheckBox: CheckBox = findViewById(R.id.showPasswordCheckBox)
        idnum= findViewById(R.id.idnumber)
        confirmpwd = findViewById(R.id.editConfirmTextPassword)
        progress= ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()

        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                edtpwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                confirmpwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                edtpwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                confirmpwd.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        btnreg.setOnClickListener {
            var id = idnum.text.toString().trim()
            var email= edtemail.text.toString().trim()
            var password= edtpwd.text.toString().trim()
            var confirmPassword= confirmpwd.text.toString().trim()
            if (email.isEmpty()){
                edtemail.setError("Please fill this input")
                edtemail.requestFocus()
            }else if (password.isEmpty()){
                edtpwd.setError("Please fill this input")
                edtpwd.requestFocus()
            } else if(password.length < 6){
                edtpwd.setError("Password too short")
                edtpwd.requestFocus()
            }else if (password != confirmPassword) {
                confirmpwd.setError("Passwords do not match")
                confirmpwd.requestFocus()
            }else if (id.isEmpty()){
                idnum.setError("Please input your ID Number!")
                idnum.requestFocus()
            }else if (id.length < 8 ){
                idnum.setError("Enter a valid ID Number")
                idnum.requestFocus()
            }else{
                progress.setTitle("Registering")
                progress.setMessage("Please wait...")
                progress.show()
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{
                        progress.dismiss()
                        if (it.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            mAuth.signOut()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }else{
                            displayMessage("ERROR", it.exception!!.message.toString())
                        }
                    }
            }


        }
        tvlogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            progress= ProgressDialog(this)
            progress.setTitle("Loading")
            progress.setMessage("Please wait...")
            progress.show()
        }
    }

    fun displayMessage(title:String, message:String){
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", null)
        alertDialog.create().show()
    }
}