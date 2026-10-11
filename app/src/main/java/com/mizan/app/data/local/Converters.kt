package com.mizan.app.data.local

import androidx.room.TypeConverter
import com.mizan.app.data.local.entities.DebtCategory
import com.mizan.app.data.local.entities.DebtType
import com.mizan.app.data.local.entities.ExpenseCategory

class Converters {
    // DebtType
    @TypeConverter
    fun fromDebtType(value: DebtType?): String? = value?.name

    @TypeConverter
    fun toDebtType(value: String?): DebtType? = value?.let { DebtType.valueOf(it) }

    // DebtCategory
    @TypeConverter
    fun fromDebtCategory(value: DebtCategory?): String? = value?.name

    @TypeConverter
    fun toDebtCategory(value: String?): DebtCategory? = value?.let { DebtCategory.valueOf(it) }

    // ExpenseCategory
    @TypeConverter
    fun fromExpenseCategory(value: ExpenseCategory?): String? = value?.name

    @TypeConverter
    fun toExpenseCategory(value: String?): ExpenseCategory? = value?.let { ExpenseCategory.valueOf(it) }

    // epoch millis passthrough (Room stores Long natively) — keep for clarity
    @TypeConverter
    fun fromTimestamp(value: Long?): Long? = value

    @TypeConverter
    fun toTimestamp(value: Long?): Long? = value
}
