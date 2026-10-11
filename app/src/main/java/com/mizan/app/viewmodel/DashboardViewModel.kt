package com.mizan.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mizan.app.data.local.entities.DebtType
import com.mizan.app.repository.AppRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar

data class DashboardState(
    val totalOwedToMe: Double = 0.0,
    val totalIowe: Double = 0.0,
    val netBalance: Double = 0.0,
    val expensesThisMonth: Double = 0.0
)

class DashboardViewModel(private val repo: AppRepository) : ViewModel() {

    // أحسب بداية الشهر ونطاق الوقت مرة واحدة عند الإنشاء
    private val monthRange: Pair<Long, Long> by lazy {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val from = cal.timeInMillis
        val to = System.currentTimeMillis()
        from to to
    }

    // ثلاثة flows: ديون لي، ديون عليّ، ومجموع المصروفات لهذا الشهر
    private val owedFlow = repo.getTotalDebtByType(DebtType.OwedToMe)
    private val oweFlow = repo.getTotalDebtByType(DebtType.Owe)
    private val expensesFlow = repo.getTotalExpensesBetween(monthRange.first, monthRange.second)

    // حالة واحدة مشتقة من الثلاثة flows باستخدام combine، محفوظة كـ StateFlow
    val state: StateFlow<DashboardState> = combine(owedFlow, oweFlow, expensesFlow) { owed, owe, expenses ->
        DashboardState(
            totalOwedToMe = owed,
            totalIowe = owe,
            netBalance = owed - owe,
            expensesThisMonth = expenses
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardState()
    )
}
