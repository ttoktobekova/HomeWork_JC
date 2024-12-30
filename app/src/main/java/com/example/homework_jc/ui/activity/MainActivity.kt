package com.example.homework_jc.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.homework_jc.data.nav.MainNavScreen
import com.example.homework_jc.ui.theme.HomeWork_JCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeWork_JCTheme {
                MaterialTheme {
                    MainNavScreen()
                }
            }
        }
    }
}
