package com.example.onboardingscreen_incompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.onboardingscreen_incompose.ui.theme.OnBoardingScreen_InComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnBoardingScreen_InComposeTheme {
                IntroScreen()
            }
        }
    }
}


data class OnboardingScreen(
    val title: String,
    @DrawableRes val res: Int,
    val des: String
)


fun getList(): List<OnboardingScreen> {
    return listOf(
        OnboardingScreen(
            "Verified",
            R.drawable.first,
            "Verification is an extra or final bit of proof that establishes something is true"
        ),
        OnboardingScreen(
            "Make it simple",
            R.drawable.second,
            "We pay attention to all of your payments and find way for saving your money"
        ),
        OnboardingScreen(
            "New Banking",
            R.drawable.third,
            "Free Advisory service,mobile banking application,visa"
        ),
        OnboardingScreen(
            "Zero Fees",
            R.drawable.fourth,
            "Bank your life,We create something new you have never seen before"
        )
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen() {
    val pagerState = rememberPagerState()
    val list = getList()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(.75f)
                .fillMaxWidth()
        ) {
            HorizontalPager(count = list.size, state = pagerState) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = list.get(currentPage).title,
                        style = MaterialTheme.typography.bodyLarge, color = Color.Black
                    )

                    AsyncImage(
                        model = list.get(currentPage).res,
                        contentDescription = "",
                        modifier = Modifier
                            .height(380.dp)
                            .width(300.dp)
                    )

                    Text(
                        text = list.get(currentPage).des,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )

                }

            }


        }
        HorizontalPagerIndicator(pagerState = pagerState, modifier = Modifier.padding(26.dp))
    }

}