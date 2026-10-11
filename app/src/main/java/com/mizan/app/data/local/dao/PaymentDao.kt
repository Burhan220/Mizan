package com.mizan.app.data.local.dao

import androidx.room.*
import com.mizan.app.data.local.entities.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: Payment): Long

    @Update
    suspend fun updatePayment(payment: Payment)

    @Delete
    suspend fun deletePayment(payment: Payment)

    @Query("SELECT * FROM payments WHERE debtId = :debtId ORDER BY date DESC")
    fun getPaymentsForDebt(debtId: Long): Flow<List<Payment>>

    @Query("SELECT SUM(amount) FROM payments WHERE debtId = :debtId")
    fun getTotalPaymentsForDebt(debtId: Long): Flow<Double?>
}
