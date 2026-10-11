package com.mizan.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mizan.app.data.local.dao.DebtWithPayments
import com.mizan.app.data.local.entities.Debt
import com.mizan.app.data.local.entities.Payment
import com.mizan.app.repository.AppRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DebtViewModel(private val repo: AppRepository) : ViewModel() {

    val debtsWithPayments: StateFlow<List<DebtWithPayments>> =
        repo.getAllDebtsWithPayments().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addDebt(debt: Debt) {
        viewModelScope.launch {
            repo.addDebt(debt)
        }
    }

    fun addPayment(payment: Payment) {
        viewModelScope.launch {
            repo.addPayment(payment)
        }
    }

    fun getRemainingForDebt(principal: Double, payments: List<Payment>): Double {
        val paid = payments.sumOf { it.amount }
        return (principal - paid).coerceAtLeast(0.0)
    }
}
