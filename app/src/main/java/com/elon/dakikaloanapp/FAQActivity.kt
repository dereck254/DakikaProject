package com.elon.dakikaloanapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FAQActivity : AppCompatActivity() {

    private lateinit var faqRecyclerView: RecyclerView
    private lateinit var faqAdapter: FAQAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        // Initialize RecyclerView and Adapter
        faqRecyclerView = findViewById(R.id.faqRecyclerView) // Replace with your RecyclerView ID
        faqAdapter = FAQAdapter(generateFAQs())

        // Set RecyclerView properties
        faqRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FAQActivity)
            adapter = faqAdapter
        }
    }

    // Function to generate the list of FAQs
    private fun generateFAQs(): List<FAQAdapter.FAQ> {
        val faqList = mutableListOf<FAQAdapter.FAQ>()

        // Add your FAQ items here
        faqList.add(FAQAdapter.FAQ("What are the eligibility requirements for a loan?", "To be eligible for a loan, you must be at least 18 years old, have a valid ID, a regular source of income, and meet our credit criteria."))
        faqList.add(FAQAdapter.FAQ("What is the maximum loan amount I can apply for?", "The maximum loan amount you can apply for depends on various factors, including your income, credit history, and loan repayment capacity."))
        faqList.add(FAQAdapter.FAQ("How can I apply for a loan?", "You can apply for a loan by filling out the online application form on our website or through our mobile app."))
        faqList.add(FAQAdapter.FAQ("What documents are required for the loan application?", "You will need to provide proof of identity, proof of address, and proof of income."))
        faqList.add(FAQAdapter.FAQ("How long does it take to get loan approval?", "Loan approval typically takes 24-48 hours once we receive all the required documents."))
        faqList.add(FAQAdapter.FAQ("What is the loan repayment period?", "The loan repayment period can vary based on the loan amount and terms. It usually ranges from 3 months to 2 years."))
        faqList.add(FAQAdapter.FAQ("How much loan amount can I get?", "The loan amount you can get depends on various factors such as your income, credit history, and repayment capacity."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan with a low credit score?", "Yes, we consider loan applications from individuals with varying credit scores. Your eligibility and terms may be influenced by your credit score."))
        faqList.add(FAQAdapter.FAQ("What are the interest rates for DakikaLoan?", "The interest rates for DakikaLoan vary based on the loan amount, repayment period, and other factors. Contact our customer support for specific details."))
        faqList.add(FAQAdapter.FAQ("Is there an application fee for DakikaLoan?", "No, we do not charge any application fee for DakikaLoan."))
        faqList.add(FAQAdapter.FAQ("How can I track the status of my loan application?", "You can track the status of your loan application by logging into your account on our website or through the mobile app."))
        faqList.add(FAQAdapter.FAQ("What happens if I miss a loan repayment?", "If you miss a loan repayment, late payment fees may apply, and it could have a negative impact on your credit score. Contact us as soon as possible if you anticipate difficulty in making a payment."))
        faqList.add(FAQAdapter.FAQ("Can I prepay my loan before the due date?", "Yes, you can prepay your loan before the due date. Contact our customer support to get the necessary information and process for prepayment."))
        faqList.add(FAQAdapter.FAQ("Can I apply for multiple loans at the same time?", "No, you can only have one active loan at a time. Once you repay your existing loan, you can apply for a new one if needed."))
        faqList.add(FAQAdapter.FAQ("What happens if my loan application is rejected?", "If your loan application is rejected, you will receive a notification with the reason for rejection. You can contact our customer support for further assistance or explore other loan options."))
        faqList.add(FAQAdapter.FAQ("Can I change the loan amount or repayment period after approval?", "Once your loan is approved, you cannot change the loan amount or repayment period. It is recommended to carefully review the terms before finalizing the loan application."))
        faqList.add(FAQAdapter.FAQ("What happens if I change my job during the loan repayment period?", "If you change your job during the loan repayment period, please inform us. It may affect the loan terms, and we may need updated income documentation."))
        faqList.add(FAQAdapter.FAQ("How can I contact customer support?", "You can contact our customer support through the contact details provided on our website or within the mobile app. We are available to assist you with any queries or concerns."))
        faqList.add(FAQAdapter.FAQ("Are my personal and financial details secure?", "Yes, we prioritize the security and confidentiality of your personal and financial information. We employ industry-standard security measures to protect your data."))
        faqList.add(FAQAdapter.FAQ("What happens if I want to cancel my loan application?", "If you want to cancel your loan application, please contact our customer support as soon as possible. They will guide you through the cancellation process."))
        faqList.add(FAQAdapter.FAQ("Can I change the bank account linked to my loan?", "Yes, you can change the bank account linked to your loan. Contact our customer support to provide the necessary information and initiate the account update process."))
        faqList.add(FAQAdapter.FAQ("What if I have trouble making loan repayments due to unexpected circumstances?", "If you have trouble making loan repayments due to unexpected circumstances, such as a medical emergency or job loss, please contact our customer support immediately. We may have options to assist you during such times."))
        faqList.add(FAQAdapter.FAQ("What is the loan disbursement process?", "Once your loan is approved, the funds will be transferred directly to your bank account provided during the application process. The disbursement usually occurs within a few business days."))
        faqList.add(FAQAdapter.FAQ("Can I extend the loan repayment period?", "In certain cases, we may consider requests for loan repayment period extensions. Contact our customer support well before the loan maturity date to discuss your situation and explore available options."))
        faqList.add(FAQAdapter.FAQ("What if I want to close my loan before the maturity date?", "If you want to close your loan before the maturity date, please contact our customer support for the necessary details and procedures for early loan closure."))
        faqList.add(FAQAdapter.FAQ("Can I make partial loan repayments?", "Yes, you can make partial loan repayments, but it is recommended to make full repayments whenever possible. Partial repayments may affect the interest calculation and loan closure process."))
        faqList.add(FAQAdapter.FAQ("What happens if I provide incorrect information during the loan application?", "Providing incorrect information during the loan application can lead to rejection or issues during the loan processing. Please ensure the accuracy of the information provided to avoid any complications."))
        faqList.add(FAQAdapter.FAQ("Do you offer loan refinancing options?", "We do not currently offer loan refinancing options. Once you repay your existing loan, you can apply for a new loan if needed."))
        faqList.add(FAQAdapter.FAQ("Can I change the repayment frequency (monthly/weekly) after loan approval?", "Once your loan is approved, you cannot change the repayment frequency. The repayment frequency is determined during the application process based on the loan type and terms chosen."))
        faqList.add(FAQAdapter.FAQ("What are the consequences of defaulting on loan repayments?", "Defaulting on loan repayments can have severe consequences, such as legal actions, collection agency involvement, negative impact on your credit score, and difficulty obtaining future credit. It is important to make timely repayments."))
        faqList.add(FAQAdapter.FAQ("Can I make loan repayments through online banking?", "Yes, you can make loan repayments through online banking. Ensure that you use the correct payment details provided by our customer support or mentioned in your loan agreement."))
        faqList.add(FAQAdapter.FAQ("What happens if I want to increase my loan amount?", "If you want to increase your loan amount, you will need to apply for a new loan. The existing loan will remain unaffected, and the new loan will be subject to separate terms and approval."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I am self-employed?", "Yes, self-employed individuals can apply for a loan. You will need to provide additional documentation, such as business financial statements and tax returns, to support your income verification."))
        faqList.add(FAQAdapter.FAQ("Can I choose a specific repayment date for my loan?", "We offer flexible repayment date options, but the availability may depend on your loan type and terms. Contact our customer support to inquire about the possibility of choosing a specific repayment date."))
        faqList.add(FAQAdapter.FAQ("What is the maximum loan amount I can apply for?", "The maximum loan amount you can apply for depends on various factors, including your income, credit history, and repayment capacity. Contact our customer support to discuss your specific requirements."))
        faqList.add(FAQAdapter.FAQ("Do you charge a penalty for early loan repayment?", "No, we do not charge a penalty for early loan repayment. You can repay your loan before the maturity date without incurring any additional charges."))
        faqList.add(FAQAdapter.FAQ("Can I change my loan repayment method (e.g., from direct debit to manual payment)?", "In general, once your loan is approved, the repayment method is set. However, exceptions may be made in certain cases. Contact our customer support to discuss your specific request."))
        faqList.add(FAQAdapter.FAQ("What happens if I want to reschedule my loan repayment?", "If you want to reschedule your loan repayment, please contact our customer support as soon as possible. They will guide you through the rescheduling process, if available."))
        faqList.add(FAQAdapter.FAQ("Is there a grace period for loan repayments?", "We may offer a grace period for loan repayments in certain cases. Contact our customer support to inquire about the availability and conditions for a grace period."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I have an existing loan with another financial institution?", "Yes, you can apply for a loan with us even if you have an existing loan with another financial institution. However, your existing loan and financial obligations will be taken into consideration during the evaluation of your application."))
        faqList.add(FAQAdapter.FAQ("What are the consequences of providing false information during the loan application?", "Providing false information during the loan application can lead to rejection, legal actions, and potential liability for fraud. It is essential to provide accurate and truthful information."))
        faqList.add(FAQAdapter.FAQ("Can I change the loan purpose after approval?", "Once your loan is approved, you cannot change the loan purpose. The loan purpose is determined during the application process and is considered in the evaluation and approval of your loan."))
        faqList.add(FAQAdapter.FAQ("Do you offer loan deferment options?", "We do not currently offer loan deferment options. Loan deferment refers to temporarily suspending loan repayments. Please ensure that you can meet the loan repayment obligations before applying."))
        faqList.add(FAQAdapter.FAQ("Can I request a loan top-up after repaying a significant portion of the existing loan?", "Yes, you can request a loan top-up after repaying a significant portion of the existing loan. Contact our customer support to discuss the specific requirements and process for a loan top-up."))
        faqList.add(FAQAdapter.FAQ("What happens if I provide insufficient income documentation?", "Insufficient income documentation may result in the rejection of your loan application or an adjustment in the loan amount. It is important to provide complete and accurate income documentation to support your loan application."))
        faqList.add(FAQAdapter.FAQ("Can I make loan repayments from a joint bank account?", "Yes, you can make loan repayments from a joint bank account. Ensure that the joint account holder is aware and approves of the loan repayment arrangement."))
        faqList.add(FAQAdapter.FAQ("What happens if I fail to provide the required documents for the loan application?", "Failure to provide the required documents for the loan application may result in the rejection or delay of your loan application. Please ensure you submit all the necessary documents within the specified timeframe."))
        faqList.add(FAQAdapter.FAQ("Do you offer loan insurance or protection plans?", "We may offer loan insurance or protection plans. Contact our customer support to inquire about the availability and details of loan insurance or protection options."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I am a non-resident or foreign national?", "Yes, non-residents or foreign nationals may be eligible to apply for a loan. Additional documentation and requirements may apply. Contact our customer support for more information."))
        faqList.add(FAQAdapter.FAQ("Can I change the loan repayment frequency (e.g., monthly to fortnightly)?", "Once your loan is approved, you cannot change the loan repayment frequency. The repayment frequency is determined during the application process and is based on the loan type and terms chosen."))
        faqList.add(FAQAdapter.FAQ("What happens if I want to change the loan repayment date?", "If you want to change the loan repayment date, please contact our customer support as early as possible. They will guide you through the process and inform you of any applicable restrictions or fees."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I have a limited credit history?", "Yes, we consider loan applications from individuals with limited credit history. However, your eligibility and terms may be influenced by your creditworthiness and other factors."))
        faqList.add(FAQAdapter.FAQ("What happens if I miss a loan repayment due to a technical issue or error?", "If you miss a loan repayment due to a technical issue or error, please contact our customer support immediately. They will assist you in resolving the issue and mitigating any potential consequences."))
        faqList.add(FAQAdapter.FAQ("Can I use collateral to secure a loan?", "We do not currently accept collateral to secure loans. Our loans are typically unsecured, meaning they do not require collateral."))
        faqList.add(FAQAdapter.FAQ("Do you offer loan repayment extensions?", "In certain cases, we may consider loan repayment extensions. Contact our customer support well before the loan maturity date to discuss your situation and explore available options."))
        faqList.add(FAQAdapter.FAQ("What happens if I want to change my loan repayment method (e.g., from manual payment to direct debit)?", "In general, once your loan is approved, the repayment method is set. However, exceptions may be made in certain cases. Contact our customer support to discuss your specific request."))
        faqList.add(FAQAdapter.FAQ("What happens if I want to decrease my loan amount?", "If you want to decrease your loan amount, please contact our customer support as soon as possible. They will guide you through the necessary steps to adjust the loan amount and provide information about any applicable fees or restrictions."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I have a previous bankruptcy record?", "We consider loan applications from individuals with previous bankruptcy records. However, your eligibility and terms may be influenced by your creditworthiness and the length of time since the bankruptcy discharge."))
        faqList.add(FAQAdapter.FAQ("What happens if I want to change the loan repayment frequency (e.g., fortnightly to weekly)?", "Once your loan is approved, you cannot change the loan repayment frequency. The repayment frequency is determined during the application process and is based on the loan type and terms chosen."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I am currently unemployed?", "We typically require a steady source of income to approve loan applications. If you are currently unemployed, your eligibility for a loan may be affected. Contact our customer support to discuss your situation and explore available options."))
        faqList.add(FAQAdapter.FAQ("Can I make loan repayments through mobile payment apps?", "We may accept loan repayments through specific mobile payment apps. Contact our customer support to inquire about the available payment methods and provide you with the necessary payment details."))
        faqList.add(FAQAdapter.FAQ("What happens if I fail to repay the loan?", "Failing to repay the loan can have serious consequences, including legal actions, collection agency involvement, negative impact on your credit score, and difficulty obtaining future credit. It is crucial to honor your loan repayment obligations."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I have previously defaulted on a loan?", "We consider loan applications from individuals with a history of loan default. However, your eligibility and terms may be influenced by your creditworthiness and the circumstances surrounding the previous default."))
        faqList.add(FAQAdapter.FAQ("Do you offer loan modifications or restructuring options?", "We may offer loan modifications or restructuring options in certain cases. Contact our customer support to discuss your situation and explore available options."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I have a low income?", "We consider loan applications from individuals with varying income levels. However, your eligibility and loan amount may be influenced by your income and repayment capacity. Contact our customer support to discuss your specific situation."))
        faqList.add(FAQAdapter.FAQ("What happens if I want to change the loan repayment duration?", "If you want to change the loan repayment duration, please contact our customer support as early as possible. They will guide you through the process and inform you of any applicable restrictions or fees."))
        faqList.add(FAQAdapter.FAQ("Can I apply for a loan if I have an existing loan with DakikaLoan?", "Yes, you can apply for a new loan even if you have an existing loan with DakikaLoan. However, your existing loan and financial obligations will be taken into consideration during the evaluation of your application."))
        // Add more FAQs here

        return faqList
    }
    override fun onBackPressed() {
        // Redirect to the HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}




