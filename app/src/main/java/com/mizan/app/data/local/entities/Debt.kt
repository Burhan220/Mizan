package com.mizan.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class DebtType {
    OwedToMe, // لي (شخص مدين لي)
    Owe       // عليّ (أنا مدين له)
}

enum class DebtCategory {
    Personal,
    Work,
    Family
}

@Entity(tableName = "debts")
data class Debt(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String, // اسم الشخص أو الجهة
    val principal: Double,
    val date: Long, // epoch millis
    val notes: String? = null,
    val type: DebtType = DebtType.OwedToMe,
    val category: DebtCategory = DebtCategory.Personal,
    val createdAt: Long = System.currentTimeMillis()
)
