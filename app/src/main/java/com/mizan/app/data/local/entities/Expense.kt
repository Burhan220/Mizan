package com.mizan.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class ExpenseCategory {
    Food,
    Transport,
    Bills,
    Shopping,
    Health,
    Education,
    Other
}

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val category: ExpenseCategory = ExpenseCategory.Other,
    val date: Long,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
