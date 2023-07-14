package com.elon.dakikaloanapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class MyLoanActivity : AppCompatActivity() {

    private lateinit var etAge: EditText
    private lateinit var spLoanAmount: Spinner
    private lateinit var spRepayTerm: Spinner
    private lateinit var spVoucher: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_loan)

        spLoanAmount = findViewById(R.id.spLoanAmount)
        spRepayTerm = findViewById(R.id.spRepayTerm)
        spVoucher = findViewById(R.id.spVoucher)

        val btnApplyNow = findViewById<Button>(R.id.btnApplyNow)
        btnApplyNow.setOnClickListener {
            Toast.makeText(this, "You do not qualify at the moment. keep the app installed and try later", Toast.LENGTH_SHORT).show()
            if (isFormValid()) {
                applyForLoan()
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isFormValid(): Boolean {
        return spLoanAmount.selectedItemPosition > 0 && spRepayTerm.selectedItemPosition > 0 && spVoucher.selectedItemPosition > 0
    }

    private fun applyForLoan() {

        // Get the selected loan amount, repay term, and voucher
        val loanAmount = spLoanAmount.selectedItem.toString()
        val repayTerm = spRepayTerm.selectedItem.toString()
        val voucher = spVoucher.selectedItem.toString()

        // Check if the user qualifies for the loan
        val qualifiesForLoan = checkLoanEligibility(loanAmount, repayTerm, voucher)

        // Calculate service fee (originations fee) and disbursed amount if the user qualifies for the loan
        val serviceFee = if (qualifiesForLoan) {
            (loanAmount.toDouble() * 0.2).toInt()
        } else {
            0
        }
        val disbursedAmount = if (qualifiesForLoan) {
            loanAmount.toDouble() - serviceFee
        } else {
            0.0
        }

        // Display a message based on eligibility, service fee, and disbursed amount
        val message = if (qualifiesForLoan) {
            "Congratulations! You qualify for a loan of KES $loanAmount. " +
                    "The service fee (originations fee) is KES $serviceFee. " +
                    "The disbursed amount to your M-Pesa number will be KES $disbursedAmount."
        } else {
            "Sorry, you do not qualify for a loan at the moment."
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun checkLoanEligibility(loanAmount: String, repayTerm: String, voucher: String): Boolean {
        val maximumRepayTerm = 90 // Maximum term is 3 months (90 days)

        // Check loan amount eligibility based on age


        // Check repay term eligibility
        val validRepayTerm = (repayTerm.toInt() % 7 == 0) && (repayTerm.toInt() <= maximumRepayTerm)

        // Return eligibility based on loan amount, repay term, and age limit
        return (loanAmount <= loanAmount) && validRepayTerm
    }

    override fun onBackPressed() {
        // Redirect to the HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}

