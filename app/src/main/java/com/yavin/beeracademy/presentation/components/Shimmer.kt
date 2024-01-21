package com.yavin.beeracademy.presentation.components

import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Shimmer(
    modifier: Modifier = Modifier,
    baseColor: Color = Color.LightGray
) {
    var componentWidthPx by remember { mutableIntStateOf(0) }
    var componentHeightPx by remember { mutableIntStateOf(0) }
    Box(
        modifier = modifier
            .onGloballyPositioned {
                componentHeightPx = it.size.height
                componentWidthPx = it.size.width
            }
    ) {
        ShimmerBackground(componentWidthPx, componentHeightPx, baseColor)
    }
}

@Preview
@Composable
fun ShimmerBackgroundPreview() {
    ShimmerBackground(
        100, 100,
        baseColor = Color.LightGray
    )
}

@Composable
private fun ShimmerBackground(
    componentWidthPx: Int,
    componentHeightPx: Int,
    baseColor: Color,
) {
    val gradientWidth: Float = 0.4f * LocalConfiguration.current.screenWidthDp
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val xCardShimmer = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (componentWidthPx + gradientWidth),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = EaseInCirc,
                delayMillis = 100
            ),
            repeatMode = RepeatMode.Restart
        ), label = "xCardShimmer"
    )
    val yCardShimmer = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (componentHeightPx + gradientWidth),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = EaseOutCirc,
                delayMillis = 100
            ),
            repeatMode = RepeatMode.Restart
        ), label = "yCardShimmer"
    )

    val colors = listOf(
        baseColor,
        baseColor.copy(alpha = .3f),
        baseColor,
    )

    ShimmerSurface(
        colors = colors,
        xShimmer = xCardShimmer.value,
        yShimmer = yCardShimmer.value,
        gradientWidth = gradientWidth
    )
}

@Composable
private fun ShimmerSurface(
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    gradientWidth: Float
) {
    val brush = Brush.linearGradient(
        colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )

    Surface {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush)
        )
    }
}