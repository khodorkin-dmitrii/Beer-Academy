package com.yavin.beeracademy.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.compose.AsyncImage
import com.yavin.beeracademy.R
import com.yavin.beeracademy.domain.Beer
import com.yavin.beeracademy.ui.debugPlaceholder
import com.yavin.beeracademy.ui.theme.BeerAcademyTheme

@Composable
fun BeerDetailsScreen(beer: Beer) {
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        AsyncImage(
            model = beer.imageUrl,
            placeholder = debugPlaceholder(R.drawable.img_preview),
            contentDescription = beer.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
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
            color = if (isDarkTheme) Color.Cyan else Color.DarkGray,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = beer.description,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis
        )
    }

}

@Preview(showBackground = true)
@Composable
fun BeerDetailsScreenPreview(
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