package com.mizan.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mizan.app.data.local.entities.Salary
import com.mizan.app.repository.AppRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SalaryViewModel(private val repo: AppRepository) : ViewModel() {

    val salaries: StateFlow<List<Salary>> =
        repo.getAllSalaries().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addSalary(salary: Salary) {
        viewModelScope.launch {
            repo.addSalary(salary)
        }
    }
}
