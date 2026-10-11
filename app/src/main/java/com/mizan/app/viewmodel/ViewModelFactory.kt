package com.mizan.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mizan.app.repository.AppRepository

/**
 * ViewModelFactory بسيط لتمرير AppRepository إلى ViewModels بدون Hilt.
 */
class ViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) ->
                DashboardViewModel(repository) as T
            modelClass.isAssignableFrom(DebtViewModel::class.java) ->
                DebtViewModel(repository) as T
            modelClass.isAssignableFrom(ExpenseViewModel::class.java) ->
                ExpenseViewModel(repository) as T
            modelClass.isAssignableFrom(SalaryViewModel::class.java) ->
                SalaryViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
