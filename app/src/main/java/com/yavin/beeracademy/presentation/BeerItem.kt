package com.yavin.beeracademy.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.yavin.beeracademy.R
import com.yavin.beeracademy.domain.Beer
import com.yavin.beeracademy.ui.debugPlaceholder
import com.yavin.beeracademy.ui.theme.BeerAcademyTheme
import com.yavin.beeracademy.ui.theme.Purple40
import com.yavin.beeracademy.ui.theme.Teal100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerItem(
    beer: Beer,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val isDarkTheme = isSystemInDarkTheme()
    Card(
        modifier = modifier.padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = { onClick(beer.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 16.dp, 16.dp, 16.dp)
        ) {
            AsyncImageWithPreview(
                url = beer.imageUrl,
                modifier = Modifier.size(90.dp),
                contentDescription = beer.name
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = beer.tagline,
                    fontStyle = FontStyle.Italic,
                    color = if (isDarkTheme) Teal100 else Purple40,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = beer.description,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                beer.firstBrewed?.let {
                    Text(
                        text = it,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        fontSize = 8.sp
                    )
                }
            }
        }
    }
}

@Composable
fun AsyncImageWithPreview(
    url: String?,
    modifier: Modifier = Modifier,
    contentDescription: String = "beer_image"
) {
    if (LocalInspectionMode.current) {
        AsyncImage(
            model = url,
            placeholder = debugPlaceholder(R.drawable.img_preview),
            contentDescription = contentDescription,
            filterQuality = FilterQuality.Low,
            modifier = modifier
        )
    } else {
        SubcomposeAsyncImage(
            model = url,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            },
            contentDescription = contentDescription,
            filterQuality = FilterQuality.Low,
            modifier = modifier
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun BeerItemDayPreview(
    beer: Beer = Beer(
        id = 1,
        name = "Light Beer",
        tagline = "This is cool beer",
        firstBrewed = "07/2023",
        description = "This is a description for a beer. \nThis is just a next line.",
        imageUrl = null
    )
) {
    BeerAcademyTheme {
        BeerItem(
            beer = beer,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BeerItemNightPreview(
    beer: Beer = Beer(
        id = 1,
        name = "Dark Beer",
        tagline = "This is cool beer",
        firstBrewed = "07/2023",
        description = "This is a description for a beer. \nThis is just a next line.",
        imageUrl = null
    )
) {
    BeerAcademyTheme {
        BeerItem(
            beer = beer,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )
    }
}
