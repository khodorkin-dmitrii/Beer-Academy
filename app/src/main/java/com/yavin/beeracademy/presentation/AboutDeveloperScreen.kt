package com.yavin.beeracademy.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yavin.beeracademy.R

@Composable
fun AboutDeveloperScreen() {
    val isDarkTheme = isSystemInDarkTheme()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AboutDeveloperScreenPreview() {
    AboutDeveloperScreen()
}