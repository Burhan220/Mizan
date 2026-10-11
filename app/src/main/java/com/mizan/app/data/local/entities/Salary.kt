package com.mizan.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salaries")
data class Salary(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val month: Int, // 1..12
    val year: Int,
    val source: String = "Salary", // Salary, Bonus, Freelance, ...
    val createdAt: Long = System.currentTimeMillis()
)
