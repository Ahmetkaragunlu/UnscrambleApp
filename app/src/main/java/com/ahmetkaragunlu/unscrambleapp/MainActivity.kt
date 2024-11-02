package com.ahmetkaragunlu.unscrambleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.ahmetkaragunlu.unscrambleapp.ui.theme.UnscrambleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnscrambleAppTheme {
               Surface(color = MaterialTheme.colorScheme.background) {
                GameScreen()
               }
            }
        }
    }
}

