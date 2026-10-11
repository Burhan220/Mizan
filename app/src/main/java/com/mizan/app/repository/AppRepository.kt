package com.mizan.app.repository

import com.mizan.app.data.local.AppDatabase
import com.mizan.app.data.local.dao.DebtWithPayments
import com.mizan.app.data.local.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository(private val db: AppDatabase) {

    // Debts
    fun getAllDebtsWithPayments(): Flow<List<DebtWithPayments>> = db.debtDao().getAllDebtsWithPayments()

    suspend fun addDebt(debt: Debt): Long = db.debtDao().insertDebt(debt)

    suspend fun updateDebt(debt: Debt) = db.debtDao().updateDebt(debt)

    suspend fun deleteDebt(debt: Debt) = db.debtDao().deleteDebt(debt)

    // Payments
    suspend fun addPayment(payment: Payment): Long = db.paymentDao().insertPayment(payment)

    suspend fun updatePayment(payment: Payment) = db.paymentDao().updatePayment(payment)

    fun getPaymentsForDebt(debtId: Long) = db.paymentDao().getPaymentsForDebt(debtId)

    fun getTotalPaymentsForDebt(debtId: Long): Flow<Double> =
        db.paymentDao().getTotalPaymentsForDebt(debtId).map { it ?: 0.0 }

    // Totals by debt type
    fun getTotalDebtByType(type: DebtType): Flow<Double> =
        db.debtDao().getTotalByType(type.name).map { it ?: 0.0 }

    // Expenses
    fun getAllExpenses(): Flow<List<Expense>> = db.expenseDao().getAllExpenses()
    suspend fun addExpense(expense: Expense): Long = db.expenseDao().insertExpense(expense)
    suspend fun updateExpense(expense: Expense) = db.expenseDao().updateExpense(expense)
    suspend fun deleteExpense(expense: Expense) = db.expenseDao().deleteExpense(expense)
    fun getExpensesByCategory(category: ExpenseCategory) = db.expenseDao().getExpensesByCategory(category)
    fun getTotalExpensesBetween(from: Long, to: Long) =
        db.expenseDao().getTotalExpensesBetween(from, to).map { it ?: 0.0 }

    // Salaries / income
    fun getAllSalaries() = db.salaryDao().getAllSalaries()
    suspend fun addSalary(salary: Salary): Long = db.salaryDao().insertSalary(salary)
    fun getTotalIncomeForMonth(month: Int, year: Int) =
        db.salaryDao().getTotalIncomeForMonth(month, year).map { it ?: 0.0 }
}
