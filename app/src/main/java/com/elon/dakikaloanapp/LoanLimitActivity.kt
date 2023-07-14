package com.elon.dakikaloanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoanLimitActivity : AppCompatActivity() {

    private lateinit var ageEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var loanLimitTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_limit)

        ageEditText = findViewById(R.id.age_edittext)
        calculateButton = findViewById(R.id.calculate_button)
        loanLimitTextView = findViewById(R.id.loan_limit_textview)

        calculateButton.setOnClickListener {
            calculateLoanLimit()
        }
    }

    private fun calculateLoanLimit() {
        val ageText = ageEditText.text.toString().trim()

        if (ageText.isNotEmpty()) {
            val age = ageText.toInt()
            val loanLimit = calculateLoanLimitBasedOnAge(age)
            loanLimitTextView.text = "Loan Limit: $loanLimit"
        } else {
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateLoanLimitBasedOnAge(age: Int): Int {
        return when {
            age < 18 -> 0
            age < 25 -> 1000
            age < 35 -> 5000
            age < 50 -> 10000
            else -> 20000
        }
    }
    private fun navigateToLoanActivity() {
        // Check if the user qualifies for the loan based on your criteria
        val qualifiesForLoan = true // Replace with your logic

        if (qualifiesForLoan) {
            val intent = Intent(this, MyLoanActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "You do not qualify for the loan", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onBackPressed() {
        // Redirect to the HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}
