package com.mizan.app.data.local.dao

import androidx.room.*
import com.mizan.app.data.local.entities.Debt
import com.mizan.app.data.local.entities.Payment
import kotlinx.coroutines.flow.Flow

/**
 * Relation-holder: Debt مع قائمة الدفعات المرتبطة به.
 */
data class DebtWithPayments(
    @Embedded val debt: Debt,
    @Relation(parentColumn = "id", entityColumn = "debtId")
    val payments: List<Payment>
)

@Dao
interface DebtDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebt(debt: Debt): Long

    @Update
    suspend fun updateDebt(debt: Debt)

    @Delete
    suspend fun deleteDebt(debt: Debt)

    @Transaction
    @Query("SELECT * FROM debts ORDER BY date DESC")
    fun getAllDebtsWithPayments(): Flow<List<DebtWithPayments>>

    @Query("SELECT * FROM debts WHERE id = :id")
    suspend fun getDebtById(id: Long): Debt?

    @Query("SELECT SUM(principal) FROM debts WHERE type = :type")
    fun getTotalByType(type: String): Flow<Double?>
}
