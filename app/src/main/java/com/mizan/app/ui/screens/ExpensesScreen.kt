package com.mizan.app.ui.screens

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
import com.mizan.app.data.local.entities.Expense
import com.mizan.app.di.AppModule
import com.mizan.app.viewmodel.ExpenseViewModel
import com.mizan.app.viewmodel.ViewModelFactory

@Composable
fun ExpensesScreen(onNavigate: (String) -> Unit = {}) {
    val context = LocalContext.current
    val repo = AppModule.provideRepository(context)
    val factory = ViewModelFactory(repo)
    val vm: ExpenseViewModel = viewModel(factory = factory)

    val expenses by vm.expenses.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "المصروفات", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(expenses) { expense: Expense ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = expense.category.name, style = MaterialTheme.typography.subtitle1)
                        Text(text = expense.notes ?: "", style = MaterialTheme.typography.body2)
                    }
                    Text(text = "%.2f".format(expense.amount))
                }
                Divider()
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { /* مستقبلًا: فتح حوار إضافة مصروف */ }) {
            Text(text = "إضافة مصروف")
        }
    }
}
