package com.elon.dakikaloanapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.ActivityNotFoundException
import android.net.Uri
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.Toast

class PayLoanActivity : AppCompatActivity() {
    lateinit var back: Button
    private lateinit var amountEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var payNowButton: Button
    private lateinit var paymentInfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_loan)
        back = findViewById(R.id.gobackbutton)
        amountEditText = findViewById(R.id.editTextAmount)
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber)
        payNowButton = findViewById(R.id.buttonPayNow)
        paymentInfoTextView = findViewById(R.id.textViewPaymentInfo)

        payNowButton.setOnClickListener {
            val amount = amountEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()

            if (amount.isNotEmpty() && phoneNumber.isNotEmpty()) {
                initiatePaymentViaDakikaLoan(amount, phoneNumber)
            } else {
                Toast.makeText(this, "Please enter the loan amount and your phone number", Toast.LENGTH_SHORT).show()
            }
        }



        back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
    private fun initiatePaymentViaDakikaLoan(amount: String, phoneNumber: String) {
        try {
            val uri = Uri.parse("dakikaloan://payment?amount=$amount&phone=$phoneNumber")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "DakikaLoan app is not installed on your device", Toast.LENGTH_SHORT).show()
        }
    }

    private fun highlightText(text: String, searchText: String): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder(text)
        val startIndex = text.indexOf(searchText)
        val endIndex = startIndex + searchText.length
        val foregroundColorSpan = ForegroundColorSpan(resources.getColor(R.color.colorAccent))
        spannableStringBuilder.setSpan(foregroundColorSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableStringBuilder
    }
    override fun onBackPressed() {
        // Redirect to the HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
