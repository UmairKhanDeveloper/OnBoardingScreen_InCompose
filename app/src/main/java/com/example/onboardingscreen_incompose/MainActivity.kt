package com.example.onboardingscreen_incompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.onboardingscreen_incompose.ui.theme.OnBoardingScreen_InComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnBoardingScreen_InComposeTheme {
                App()
            }
        }
    }
}


