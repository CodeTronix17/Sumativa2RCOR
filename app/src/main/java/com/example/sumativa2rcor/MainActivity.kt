package com.example.sumativa2rcor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sumativa2rcor.ui.theme.Sumativa2RCORTheme
import com.example.sumativa2rcor.views.CalcularTotal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sumativa2RCORTheme {
                CalcularTotal()
                }
            }
        }
    }


