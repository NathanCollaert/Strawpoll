package com.example.strawpoll.ui.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.strawpoll.databinding.AnswerContentBinding
import com.example.strawpoll.domain.Answer

class CreateAdapter() : ListAdapter<Answer, CreateAdapter.ViewHolder>(CreateAnswerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position + 1)
    }

    class ViewHolder private constructor(val binding: AnswerContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Answer, position: Int) {
            binding.answer = item
            binding.answerNumber.text = position.toString()
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AnswerContentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CreateAnswerDiffCallback : DiffUtil.ItemCallback<Answer>() {
    override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem == newItem
    }
}

class Answeristener(val afterTextChangedListener: (answer: Answer) -> Unit) {
    fun onAfterTextChanged(answer: Answer) = afterTextChangedListener(answer)
}