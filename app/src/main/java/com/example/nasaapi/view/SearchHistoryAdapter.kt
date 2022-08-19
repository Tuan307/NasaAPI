package com.example.nasaapi.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapi.databinding.SearchItemBinding
import com.example.nasaapi.model.SearchItem
import com.example.nasaapi.util.MyDiffUtil

class SearchHistoryAdapter(private val listener: HandleItemClick) :
    RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {
    private var oldList: List<SearchItem> = emptyList()

    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchItem) {
            binding.apply {
                txtHistory.text = item.text
                txtHistory.setOnClickListener {
                    listener.onClickItem(item)
                }
                imgDeleteHistory.setOnClickListener {
                    listener.deleteItem(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(oldList[position])

    override fun getItemCount(): Int {
        return oldList.size
    }

    fun setData(newList: List<SearchItem>) {
        val diffUtil = MyDiffUtil(oldList, newList)
        val result = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        result.dispatchUpdatesTo(this)
    }

    interface HandleItemClick {
        fun deleteItem(search: SearchItem)
        fun onClickItem(search: SearchItem)
    }
}