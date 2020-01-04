package com.example.strawpoll.ui.result

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.strawpoll.databinding.ResultContentBinding
import com.example.strawpoll.domain.Answer
import java.math.BigDecimal
import java.math.RoundingMode

class AnswerAdapter(private val totalVotes: Int) :
    ListAdapter<Answer, AnswerAdapter.ViewHolder>(AnswerDiffCallback()) {

    init {
        usedColors.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, totalVotes)
    }

    class ViewHolder private constructor(val binding: ResultContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Answer,
            totalVotes: Int
        ) {
            val answerPercent =
                BigDecimal((100.toDouble() / totalVotes) * item.amountVoted).setScale(
                    2,
                    RoundingMode.HALF_EVEN
                )
            binding.answerString.text = item.answer
            binding.voteString.text = "${answerPercent}% (${item.amountVoted} votes)"
            binding.progressBar.progress = answerPercent.toInt()
            binding.progressBar.progressTintList =
                ColorStateList.valueOf(Color.parseColor(pickColor()))
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ResultContentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

private val colors = listOf(
    "#3eb991",
    "#ff7563",
    "#aa66cc",
    "#ffbb33",
    "#ff8800",
    "#33b5e5",
    "#aa66cc"
)

private var usedColors = mutableListOf<String>()

private fun pickColor(): String {
    return if (usedColors.isEmpty()) {
        usedColors = colors.toMutableList()
        pickColor()
    } else {
        val color = usedColors[0]
        usedColors.removeAt(0)
        color
    }
}

class AnswerDiffCallback : DiffUtil.ItemCallback<Answer>() {
    override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem == newItem
    }
}