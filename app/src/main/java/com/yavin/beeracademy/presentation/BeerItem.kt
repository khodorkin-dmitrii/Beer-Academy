package com.yavin.beeracademy.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yavin.beeracademy.domain.Beer
import com.yavin.beeracademy.ui.theme.BeerAcademyTheme

@Composable
fun BeerItem(
    beer: Beer,
    modifier: Modifier = Modifier
) {
    val isDarkTheme = isSystemInDarkTheme()
    Card(
        modifier = modifier
            .height(160.dp)
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp, 16.dp, 16.dp)
        ) {
            AsyncImage(
                model = beer.imageUrl,
                contentDescription = beer.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = beer.tagline,
                    fontStyle = FontStyle.Italic,
                    color = if (isDarkTheme) Color.Cyan else Color.DarkGray,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = beer.description,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                beer.firstBrewed?.let {
                    Spacer(modifier = Modifier.height(8.dp))
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

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun BeerItemDayPreview() {
    BeerAcademyTheme {
        BeerItem(
            beer = Beer(
                id = 1,
                name = "Light Beer",
                tagline = "This is cool beer",
                firstBrewed = "07/2023",
                description = "This is a description for a beer. \nThis is just a next line.",
                imageUrl = null
            ), modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BeerItemNightPreview() {
    BeerAcademyTheme {
        BeerItem(
            beer = Beer(
                id = 1,
                name = "Dark Beer",
                tagline = "This is cool beer",
                firstBrewed = "07/2023",
                description = "This is a description for a beer. \nThis is just a next line.",
                imageUrl = null
            ), modifier = Modifier.fillMaxWidth()
        )
    }
}