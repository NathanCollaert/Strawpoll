package com.example.strawpoll.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.strawpoll.databinding.PollListContentBinding
import com.example.strawpoll.persistence.entities.Poll

class PollAdapter(val clickListener: PollListener) :
    ListAdapter<Poll, PollAdapter.ViewHolder>(PollDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: PollListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Poll,
            clickListener: PollListener
        ) {
            binding.poll = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PollListContentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PollDiffCallback : DiffUtil.ItemCallback<Poll>() {
    override fun areItemsTheSame(oldItem: Poll, newItem: Poll): Boolean {
        return oldItem.pollId == newItem.pollId
    }

    override fun areContentsTheSame(oldItem: Poll, newItem: Poll): Boolean {
        return oldItem == newItem
    }

}

class PollListener(val clickListener: (pollId: Long) -> Unit) {
    fun onClick(poll: Poll) = clickListener(poll.pollId)
}