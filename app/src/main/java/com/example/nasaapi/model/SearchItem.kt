package com.example.nasaapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class SearchItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String
) {
}