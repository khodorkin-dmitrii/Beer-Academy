package com.yavin.beeracademy.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yavin.beeracademy.R
import com.yavin.beeracademy.domain.Beer
import com.yavin.beeracademy.ui.debugPlaceholder
import com.yavin.beeracademy.ui.theme.BeerAcademyTheme
import com.yavin.beeracademy.ui.theme.BlueGray01x50
import com.yavin.beeracademy.ui.theme.BlueGray50
import com.yavin.beeracademy.ui.theme.Purple40
import com.yavin.beeracademy.ui.theme.Teal100

@Composable
fun BeerDetailsScreen(beer: Beer) {
    val isDarkTheme = isSystemInDarkTheme()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(IntrinsicSize.Max)
            .verticalScroll(scrollState, enabled = true)
            .padding(12.dp)
    ) {
        ThemeBox(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                AsyncImage(
                    model = beer.imageUrl,
                    placeholder = debugPlaceholder(R.drawable.img_preview),
                    contentDescription = beer.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(325.dp)
                )
                beer.firstBrewed?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = beer.name,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = beer.tagline,
            fontStyle = FontStyle.Italic,
            color = if (isDarkTheme) Teal100 else Purple40,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(16.dp))

        ThemeBox(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = beer.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ThemeBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    Box(
        modifier = modifier
            .background(
                color = if (isDarkTheme) BlueGray01x50 else BlueGray50,
                shape = RoundedCornerShape(16.dp)
            ),
        content = content
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    backgroundColor = 0xFF1A191C
)
@Composable
fun BeerDetailsScreenNightPreview(
    beer: Beer = Beer(
        id = 1,
        name = "Dark Beer",
        tagline = "This is cool beer",
        firstBrewed = "07/2023",
        description = "This is a description for a beer. This is just a next phrase. And this is light beer with very long name on bottle. It is so long than should take more than 3 lines here.",
        imageUrl = null
    )
) {
    BeerAcademyTheme {
        BeerDetailsScreen(beer = beer)
    }
}

@Preview(
    showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun BeerDetailsScreenDayPreview(
    beer: Beer = Beer(
        id = 1,
        name = "Dark Beer",
        tagline = "This is cool beer",
        firstBrewed = "07/2023",
        description = "This is a description for a beer. This is just a next phrase. And this is light beer with very long name on bottle. It is so long than should take more than 3 lines here.",
        imageUrl = null
    )
) {
    BeerAcademyTheme {
        BeerDetailsScreen(beer = beer)
    }
}

