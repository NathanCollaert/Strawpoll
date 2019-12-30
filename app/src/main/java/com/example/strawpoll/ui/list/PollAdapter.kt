package com.example.strawpoll.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.strawpoll.databinding.PollListContentBinding
import com.example.strawpoll.domain.StrawpollProperty

class PollAdapter(val clickListener: PollListener) :
    ListAdapter<StrawpollProperty, PollAdapter.ViewHolder>(PollDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: PollListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: StrawpollProperty,
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

class PollDiffCallback : DiffUtil.ItemCallback<StrawpollProperty>() {
    override fun areItemsTheSame(oldItem: StrawpollProperty, newItem: StrawpollProperty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StrawpollProperty, newItem: StrawpollProperty): Boolean {
        return oldItem == newItem
    }

}

class PollListener(val clickListener: (poll: StrawpollProperty) -> Unit) {
    fun onClick(poll: StrawpollProperty) = clickListener(poll)
}