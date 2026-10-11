package com.mizan.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mizan.app.data.local.dao.DebtWithPayments
import com.mizan.app.di.AppModule
import com.mizan.app.viewmodel.DebtViewModel
import com.mizan.app.viewmodel.ViewModelFactory

@Composable
fun DebtsScreen(onNavigate: (String) -> Unit = {}) {
    val context = LocalContext.current
    val repo = AppModule.provideRepository(context)
    val factory = ViewModelFactory(repo)
    val vm: DebtViewModel = viewModel(factory = factory)

    val debts by vm.debtsWithPayments.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "الديون", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(debts) { item: DebtWithPayments ->
                val remaining = (item.debt.principal - item.payments.sumOf { it.amount }).coerceAtLeast(0.0)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { /* مستقبلًا: عرض التفاصيل */ }
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item.debt.name, style = MaterialTheme.typography.subtitle1)
                        Text(text = "المتبقي: %.2f".format(remaining), style = MaterialTheme.typography.body2)
                    }
                    Text(text = item.debt.type.name)
                }
                Divider()
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { /* مستقبلًا: فتح حوار إضافة دين */ }) {
            Text(text = "إضافة دين")
        }
    }
}
