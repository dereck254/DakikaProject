package com.elon.dakikaloanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PrivacyPolicyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        val textView:TextView = findViewById(R.id.textPrivacyPolicy)

            val privacyPolicyContent = """
            Privacy Policy
            
            Effective Date: [Date]
            
            Thank you for using DakikaLoan. This Privacy Policy outlines the types of information we collect from users of the App and how we use, store, and disclose that information. By accessing and using the App, you consent to the practices described in this Privacy Policy.
            
            1. Information We Collect
            
            1.1 Personal Information: When you use the App, we may collect certain personally identifiable information, including but not limited to:
               - Name
               - Contact information (email address, phone number)
               - Financial information (bank account details, credit score)
               - Identification information (ID number, date of birth)
               - Employment information (employer name, income details)
            
            1.2 Non-Personal Information: We may also collect non-personal information that does not directly identify you. This may include:
               - Device information (such as device type, operating system)
               - Log information (such as IP address, browser type)
               - Usage data (such as app features accessed, pages visited)
            
            2. Use of Information
            
            2.1 We use the collected information to:
               - Provide and personalize our services to you
               - Process loan applications and transactions
               - Communicate with you regarding your account and app updates
               - Improve the App's functionality and user experience
               - Comply with legal and regulatory obligations
            
            2.2 We may also use your information in an aggregated and anonymized form for statistical and research purposes.
            
            3. Data Sharing and Disclosure
            
            3.1 We may share your information with third parties under the following circumstances:
               - With your consent or at your direction
               - To process your loan application or facilitate transactions
               - With service providers who assist us in operating the App
               - To comply with legal obligations or protect our rights
            
            3.2 We will not sell, rent, or lease your personal information to third parties unless we have obtained your explicit consent to do so.
            
            4. Data Security
            
            4.1 We take reasonable measures to protect your information from unauthorized access, use, or disclosure. However, no method of transmission over the internet or electronic storage is completely secure, and we cannot guarantee absolute security.
            
            5. Children's Privacy
            
            5.1 The App is not intended for use by individuals under the age of 18. We do not knowingly collect personal information from children. If you believe we have inadvertently collected information from a child, please contact us immediately.
            
            6. Changes to this Privacy Policy
            
            6.1 We may update this Privacy Policy from time to time. Any changes will be posted on this page with a revised effective date. We encourage you to review this Privacy Policy periodically.
            
            7. Contact Us
            
            7.1 If you have any questions, concerns, or requests regarding this Privacy Policy, please contact us at [contact information].
        """.trimIndent()
            textView.text = privacyPolicyContent
            setContentView(textView)
        }
    override fun onBackPressed() {
        // Redirect to the HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
    }