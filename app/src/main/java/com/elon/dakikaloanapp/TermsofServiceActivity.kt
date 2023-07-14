package com.elon.dakikaloanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TermsofServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_termsof_service)

        val termsTextView: TextView = findViewById(R.id.termsTextView)

        val termsContent = """
            Terms of Service - Dakika Loan
            
            Last Updated: [Date]
            
            Welcome to Dakika Loan! These Terms of Service ("Terms") govern your use of the Dakika Loan platform and services ("Services"). Please read these Terms carefully before accessing or using our Services. By accessing or using the Services, you agree to be bound by these Terms and our Privacy Policy. If you do not agree with any part of these Terms, you may not use our Services.
            
            1. Eligibility:
               a. You must be at least 18 years old to use our Services.
               b. You must provide accurate and complete information during the registration process.
               c. You must comply with all applicable laws and regulations in your jurisdiction.
            
            2. Use of Services:
               a. Dakika Loan provides a platform to connect borrowers with lenders for short-term loans.
               b. By using our Services, you understand and agree that Dakika Loan is not a lender and does not provide direct loans.
               c. We do not guarantee loan approval or funding and cannot be held responsible for any lender's decision.
               d. You are solely responsible for all interactions and transactions with lenders on our platform.
            
            3. Loan Terms and Repayment:
               a. Loan terms, including interest rates, repayment schedules, and fees, are determined by the lender and agreed upon by the borrower.
               b. Dakika Loan is not responsible for the terms and conditions of the loan agreement between the borrower and lender.
               c. You must review and understand the loan agreement before accepting any loan offer.
               d. Repayment obligations and consequences for non-payment are solely between the borrower and lender.
            
            4. User Obligations:
               a. You agree to provide accurate, current, and complete information during the registration process.
               b. You are responsible for maintaining the confidentiality of your account credentials.
               c. You agree not to use the Services for any illegal or unauthorized purpose.
               d. You will not engage in any fraudulent or deceptive activities on the platform.
            
            5. Intellectual Property:
               a. All intellectual property rights in the Services, including logos, trademarks, and content, belong to Dakika Loan or its licensors.
               b. You may not use, modify, or distribute any content from our Services without prior written permission.
            
            6. Privacy:
               a. We collect and process personal information as described in our Privacy Policy.
               b. By using our Services, you consent to the collection, use, and disclosure of your personal information as outlined in our Privacy Policy.
            
            7. Limitation of Liability:
               a. Dakika Loan is not liable for any direct, indirect, incidental, consequential, or punitive damages arising from your use of the Services.
               b. We do not guarantee the accuracy, completeness, or availability of the information provided on our platform.
            
            8. Modification of Terms:
               a. Dakika Loan reserves the right to modify these Terms at any time without prior notice.
               b. Updated Terms will be posted on our platform, and your continued use of the Services constitutes acceptance of the modified Terms.
            
            9. Termination:
               a. Dakika Loan may suspend or terminate your access to the Services at any time for any reason without liability.
            
            10. Governing Law and Jurisdiction:
                a. These Terms are governed by and construed in accordance with the laws of [Jurisdiction].
                b. Any disputes arising from these Terms shall be resolved in the courts of [Jurisdiction].
            
            If you have any questions or concerns about these Terms, please contact us at [contact email].
            
            By using the Dakika Loan platform, you acknowledge that you have read, understood, and agree to abide by these Terms of Service.
        """.trimIndent()

        termsTextView.text = termsContent
    }
    override fun onBackPressed() {
        // Redirect to the HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}