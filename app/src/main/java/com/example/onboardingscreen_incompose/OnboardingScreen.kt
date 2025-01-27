package com.example.onboardingscreen_incompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

data class OnboardingScreen(
    val title: String,
    @DrawableRes val res: Int,
    val des: String
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController) {
    val list: List<OnboardingScreen> = listOf(
        OnboardingScreen(
            "Verified",
            R.drawable.first,
            "Verification is an extra or final bit of proof that establishes something is true"
        ),
        OnboardingScreen(
            "Make it simple",
            R.drawable.second,
            "We pay attention to all of your payments and find ways for saving your money"
        ),
        OnboardingScreen(
            "New Banking",
            R.drawable.third,
            "Free advisory service, mobile banking application, Visa"
        ),
        OnboardingScreen(
            "Zero Fees",
            R.drawable.fourth,
            "Bank your life, we create something new you have never seen before"
        )
    )

    IntroScreen(list = list) {
        navController.navigate(Screens.MainScreen.route) {
            popUpTo(Screens.OnboardingScreen.route) { inclusive = true }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen(
    list: List<OnboardingScreen>,
    onFinish: () -> Unit
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .fillMaxWidth()
        ) {
            HorizontalPager(count = list.size, state = pagerState) { page ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = list[page].title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )

                    AsyncImage(
                        model = list[page].res,
                        contentDescription = null,
                        modifier = Modifier
                            .height(380.dp)
                            .width(300.dp)
                    )

                    Text(
                        text = list[page].des,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                enabled = pagerState.currentPage > 0
            ) {
                Text("Back")
            }
            Button(
                onClick = {
                   coroutineScope.launch {
                        if (pagerState.currentPage < list.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            onFinish()
                        }
                    }
                }
            ) {
                Text(if (pagerState.currentPage < list.size - 1) "Next" else "Finish")
            }
        }
    }
}
