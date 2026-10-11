package com.mizan.app.data.local.dao

import androidx.room.*
import com.mizan.app.data.local.entities.Salary
import kotlinx.coroutines.flow.Flow

@Dao
interface SalaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalary(salary: Salary): Long

    @Update
    suspend fun updateSalary(salary: Salary)

    @Delete
    suspend fun deleteSalary(salary: Salary)

    @Query("SELECT * FROM salaries ORDER BY year DESC, month DESC")
    fun getAllSalaries(): Flow<List<Salary>>

    @Query("SELECT SUM(amount) FROM salaries WHERE month = :month AND year = :year")
    fun getTotalIncomeForMonth(month: Int, year: Int): Flow<Double?>
}
