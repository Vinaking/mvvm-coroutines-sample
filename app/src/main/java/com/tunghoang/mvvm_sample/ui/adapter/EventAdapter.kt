package com.tunghoang.mvvm_sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tunghoang.mvvm_sample.databinding.ItemEventBinding
import com.tunghoang.mvvm_sample.model.EventData

class EventAdapter: RecyclerView.Adapter<EventAdapter.EventViewHolder>(){

    private val differCallback = object : DiffUtil.ItemCallback<EventData>() {
        override fun areItemsTheSame(oldItem: EventData, newItem: EventData): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: EventData, newItem: EventData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = differ.currentList[position]
        holder.apply {
            binding.tvEventName.text = event.purpose
            binding.tvEventDate.text = event.dateTime
        }
    }

    inner class EventViewHolder(val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root)
}