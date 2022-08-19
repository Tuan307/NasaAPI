package com.example.nasaapi.util

import androidx.recyclerview.widget.DiffUtil
import com.example.nasaapi.model.SearchItem

class MyDiffUtil(
    private var oldList: List<SearchItem>,
    private var newList: List<SearchItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].text == newList[newItemPosition].text
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].text == newList[newItemPosition].text
    }
}