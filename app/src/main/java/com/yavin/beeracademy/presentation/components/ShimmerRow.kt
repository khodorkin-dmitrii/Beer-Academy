package com.yavin.beeracademy.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRow() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.8f)
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        (1..8).forEach { _ ->
            Shimmer(
                baseColor = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .height(122.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.NEXUS_5,
    showSystemUi = true
)
@Composable
private fun ShimmerRowPreview() {
    ShimmerRow()
}
