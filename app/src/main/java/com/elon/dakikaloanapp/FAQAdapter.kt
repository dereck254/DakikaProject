package com.elon.dakikaloanapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FAQAdapter(private val faqList: List<FAQ>) : RecyclerView.Adapter<FAQAdapter.FAQViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_faq, parent, false)
        return FAQViewHolder(view)
    }

    override fun onBindViewHolder(holder: FAQViewHolder, position: Int) {
        val faq = faqList[position]
        holder.bind(faq)
    }

    data class FAQ(
        val question: String,
        val answer: String,
        var isExpanded: Boolean = false
    )


    override fun getItemCount(): Int {
        return faqList.size
    }

    inner class FAQViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
        private val answerTextView: TextView = itemView.findViewById(R.id.answerTextView)

        init {
            questionTextView.setOnClickListener {
                val faq = faqList[adapterPosition]
                faq.isExpanded = !faq.isExpanded
                toggleAnswerVisibility()
            }
        }

        fun bind(faq: FAQ) {
            questionTextView.text = faq.question
            answerTextView.text = faq.answer
            toggleAnswerVisibility()
        }

        private fun toggleAnswerVisibility() {
            val faq = faqList[adapterPosition]
            if (faq.isExpanded) {
                answerTextView.visibility = View.VISIBLE
            } else {
                answerTextView.visibility = View.GONE
            }
        }
    }
}
