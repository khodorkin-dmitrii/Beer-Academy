package com.yavin.beeracademy.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

import com.yavin.beeracademy.domain.Beer
import com.yavin.beeracademy.ui.theme.BeerAcademyTheme

@Composable
fun BeerItem(
    beer: Beer, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = beer.imageUrl,
                contentDescription = beer.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = beer.tagline,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = beer.description,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = beer.firstBrewed,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 8.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun BeerItemPreview() {
    BeerAcademyTheme {
        BeerItem(
            beer = Beer(
                id = 1,
                name = "Beer",
                tagline = "This is cool beer",
                firstBrewed = "07/2023",
                description = "This is a description for a beer. \nThis is just a next line.",
                imageUrl = null
            ), modifier = Modifier.fillMaxWidth()
        )
    }
}