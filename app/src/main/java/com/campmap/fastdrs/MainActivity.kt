package com.campmap.fastdrs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.campmap.fastdrs.navigation.AppNavHost
import com.campmap.fastdrs.ui.theme.FastDRSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FastDRSTheme {
                AppNavHost()
            }
        }
    }
}
