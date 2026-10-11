package com.mizan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mizan.app.navigation.MizanNavGraph
import com.mizan.app.ui.theme.MizanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MizanTheme {
                Surface(modifier = Modifier) {
                    val navController = rememberNavController()
                    MizanNavGraph(navController = navController)
                }
            }
        }
    }
}
