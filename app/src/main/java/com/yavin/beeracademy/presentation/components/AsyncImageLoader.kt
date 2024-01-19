package com.yavin.beeracademy.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.yavin.beeracademy.R

@Composable
fun AsyncImageWithPreview(
    url: String?,
    modifier: Modifier = Modifier
) {
    val contentDescription: String = stringResource(R.string.content_description_beer_image)
    if (LocalInspectionMode.current) {
        AsyncImage(
            model = url,
            placeholder = debugPlaceholder(R.drawable.img_preview),
            contentDescription = contentDescription,
            filterQuality = FilterQuality.Low,
            modifier = modifier
        )
    } else {
        var isImageLoading by remember { mutableStateOf(false) }
        var isImageLoaded by remember { mutableStateOf(false) }

        Box(modifier = modifier) {
            SubcomposeAsyncImage(
                model = url,
                onLoading = {
                    isImageLoaded = false
                    isImageLoading = true
                },
                onSuccess = {
                    isImageLoaded = true
                    isImageLoading = false
                },
                onError = {
                    isImageLoaded = false
                    isImageLoading = false
                },
                contentDescription = contentDescription,
                filterQuality = FilterQuality.Low,
                modifier = modifier
            )

            AnimatedVisibility(
                visible = isImageLoading,
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Shimmer(
                    width = 90.dp,
                    height = 90.dp,
                    baseColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}