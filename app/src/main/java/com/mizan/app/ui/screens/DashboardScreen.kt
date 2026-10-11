package com.mizan.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mizan.app.di.AppModule
import com.mizan.app.viewmodel.DashboardViewModel
import com.mizan.app.viewmodel.ViewModelFactory

@Composable
fun DashboardScreen(onNavigate: (String) -> Unit = {}) {
    val context = LocalContext.current
    val repo = AppModule.provideRepository(context)
    val factory = ViewModelFactory(repo)
    val vm: DashboardViewModel = viewModel(factory = factory)

    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "ميزان", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(12.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "الرصيد الحالي: %.2f".format(state.netBalance))
                Text(text = "الديون المستحقة لي: %.2f".format(state.totalOwedToMe))
                Text(text = "الديون المستحقة عليّ: %.2f".format(state.totalIowe))
                Text(text = "مصروفات هذا الشهر: %.2f".format(state.expensesThisMonth))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onNavigate("debts") }) { Text("الديون") }
            Button(onClick = { onNavigate("expenses") }) { Text("المصروفات") }
            Button(onClick = { onNavigate("salary") }) { Text("الراتب") }
        }
    }
}
