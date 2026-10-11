package com.mizan.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mizan.app.data.local.entities.Expense
import com.mizan.app.repository.AppRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repo: AppRepository) : ViewModel() {

    val expenses: StateFlow<List<Expense>> =
        repo.getAllExpenses().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repo.addExpense(expense)
        }
    }
}
