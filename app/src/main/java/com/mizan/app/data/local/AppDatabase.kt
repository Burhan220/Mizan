package com.mizan.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mizan.app.data.local.dao.DebtDao
import com.mizan.app.data.local.dao.ExpenseDao
import com.mizan.app.data.local.dao.PaymentDao
import com.mizan.app.data.local.dao.SalaryDao
import com.mizan.app.data.local.entities.Debt
import com.mizan.app.data.local.entities.Expense
import com.mizan.app.data.local.entities.Payment
import com.mizan.app.data.local.entities.Salary

@Database(
    entities = [Debt::class, Payment::class, Expense::class, Salary::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun debtDao(): DebtDao
    abstract fun paymentDao(): PaymentDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun salaryDao(): SalaryDao
}
