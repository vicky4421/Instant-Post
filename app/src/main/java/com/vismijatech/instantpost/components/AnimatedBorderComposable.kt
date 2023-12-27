package com.vismijatech.instantpost.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vismijatech.instantpost.screens.dashboard.ProfileImage

/**
 * Created by Vivek Sawant on 04-12-2023.
 */

@Preview(showBackground = true)
@Composable
fun ABPreview() {
    AnimatedBorderComposable(
        brush = dashboardImageGradient,
        shape = CircleShape,
        animationDuration = 1000
    ) {
        ProfileImage(context = LocalContext.current, image = "")
    }
}
@Composable
fun AnimatedBorderComposable(
    brush: Brush,
    shape: Shape,
    borderWidth: Dp = 2.dp,
    animationDuration: Int,
    modifier: Modifier = Modifier,
    onContentClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite transition")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing)
        ), label = "Infinite colors"
    )
    Surface(
        modifier = Modifier.clip(shape = shape),
        shape = shape
    ) {
        Surface(
            modifier = Modifier
                .padding(all = borderWidth)
                .drawWithContent {
                    rotate(degrees = degrees) {
                        drawCircle(
                            brush = brush,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn
                        )
                    }
                    drawContent()
                },
            color = MaterialTheme.colorScheme.surface,
            shape = shape
        ) {
            content()
        }
    }
}