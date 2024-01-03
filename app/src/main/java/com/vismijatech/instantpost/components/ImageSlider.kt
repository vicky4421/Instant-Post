package com.vismijatech.instantpost.components

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.vismijatech.instantpost.model.SliderImageCardModel
import com.vismijatech.instantpost.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Vivek Sawant on 01-01-2024.
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(context: Context, cardList: List<SliderImageCardModel>) {
    val pageState = rememberPagerState(pageCount = cardList.size)
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(timeMillis = 2000)
            val nextPage = (pageState.currentPage + 1) % pageState.pageCount
            pageState.scrollToPage(page = nextPage)
        }
    }
    Box(
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(state = pageState, modifier = Modifier.fillMaxWidth()) { currentPage ->
            Card(
                modifier = Modifier
                    .size(width = Int.MAX_VALUE.dp, height = 325.dp)
                    .padding(26.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(data = cardList[currentPage].imageUrl)
                            .crossfade(enable = true)
                            .build(),
                        modifier = Modifier.size(height = 200.dp, width = Int.MAX_VALUE.dp),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "Image slider"
                    )
                    Text(
                        text = cardList[currentPage].attribution,
                        style = TextStyle(
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    )
                    Text(
                        text = cardList[currentPage].message,
                        style = TextStyle(
                            fontFamily = PoppinsFontFamily,
                        ),
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        IconButton(
            onClick = {
                val nextPage = pageState.currentPage + 1
                if (nextPage < cardList.size) {
                    scope.launch { pageState.scrollToPage(nextPage) }
                }
            },
            modifier = Modifier
                .padding(30.dp)
                .size(48.dp)
                .align(alignment = Alignment.CenterEnd)
                .clip(shape = CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0x19373737)
            )
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Right arrow",
                modifier = Modifier.fillMaxSize(),
                tint = Color.LightGray
            )
        }
        IconButton(
            onClick = {
                val prevPage = pageState.currentPage - 1
                if (prevPage >= 0) {
                    scope.launch { pageState.scrollToPage(prevPage) }
                }
            },
            modifier = Modifier
                .padding(30.dp)
                .size(48.dp)
                .align(alignment = Alignment.CenterStart)
                .clip(shape = CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0x19373737)
            )
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Right arrow",
                modifier = Modifier.fillMaxSize(),
                tint = Color.LightGray
            )
        }
        PageIndicator(
            pageCount = cardList.size,
            currentPage = pageState.currentPage,
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
        )
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorDots(isSelected = it == currentPage, modifier = modifier)
        }
    }
}

@Composable
fun IndicatorDots(isSelected: Boolean, modifier: Modifier) {
    val size = animateDpAsState(targetValue = if (isSelected) 12.dp else 10.dp, label = "")
    Box(
        modifier = modifier
            .padding(2.dp)
            .size(size = size.value)
            .clip(shape = CircleShape)
            .background(
                if (isSelected) Color(0xFF373737) else Color(0xFF373737).copy(alpha = 0.5f)
            )
    )
}
