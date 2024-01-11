package com.yavin.beeracademy.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
fun BeerDetailsScreen(
    beer: Beer,
    onBackPress: () -> Unit
) {
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
                .padding(horizontal = 44.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
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

        FoodPairing(beer.foodPairing, isDarkTheme)

        Spacer(modifier = Modifier.height(16.dp))

        ThemeBox(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Brewers Tips:",
                    fontStyle = FontStyle.Italic,
                    color = if (isDarkTheme) Teal100 else Purple40,
                    modifier = Modifier.padding(12.dp, 12.dp, 12.dp, 4.dp)
                ) // TODO localize
                Text(
                    text = beer.brewersTips,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 0.dp, 12.dp, 12.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    Box {
        IconButton(
            onClick = { onBackPress.invoke() },
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Localized description",
                tint = MaterialTheme.colorScheme.inverseSurface
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

@Composable
fun FoodPairing(points: List<String>, isDarkTheme: Boolean = false) {
    if (points.isNotEmpty()) {
        Column {
            Text(
                text = "Food Pairing:",
                fontStyle = FontStyle.Italic,
                color = if (isDarkTheme) Teal100 else Purple40,
                modifier = Modifier.padding(12.dp, 12.dp, 12.dp, 4.dp)
            ) // TODO localize
            points.forEach { point ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_cookie_12),
                        contentDescription = "", // TODO localize
                        tint = if (isDarkTheme) Teal100 else Purple40,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                    Text(text = point)
                }

            }
        }
    }
}

//region Previews

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    backgroundColor = 0xFF1A191C
)
@Composable
fun BeerDetailsScreenNightPreview(
    beer: Beer = previewBeer
) {
    BeerAcademyTheme {
        BeerDetailsScreen(beer = beer) {}
    }
}

@Preview(
    showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun BeerDetailsScreenDayPreview(
    beer: Beer = previewBeer
) {
    BeerAcademyTheme {
        BeerDetailsScreen(beer = beer) {}
    }
}

private val previewBeer = Beer(
    id = 1,
    name = "Dark Beer",
    tagline = "This is cool beer",
    firstBrewed = "07/2023",
    description = "This is a description for a beer. This is just a next phrase. And this is light beer with very long name on bottle. It is so long than should take more than 3 lines here.",
    imageUrl = null,
    foodPairing = listOf(
        "Spicy carne asada with a pico de gallo sauce",
        "Shredded chicken tacos with a mango chilli lime salsa",
        "Cheesecake with a passion fruit swirl sauce"
    ),
    brewersTips = "While it may surprise you, this version of Punk IPA isn't dry hopped but still packs a punch! To make the best of the aroma hops make sure they are fully submerged and add them just before knock out for an intense hop hit."
)

//endregion