package com.yavin.beeracademy.presentation

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.yavin.beeracademy.domain.Beer
import com.yavin.beeracademy.presentation.components.BeerItem
import com.yavin.beeracademy.presentation.components.BeerItemDayPreview
import com.yavin.beeracademy.presentation.components.BeerItemNightPreview
import com.yavin.beeracademy.presentation.components.ShimmerRow
import com.yavin.beeracademy.ui.theme.BeerAcademyTheme

@Composable
fun BeerListScreen(
    beers: LazyPagingItems<Beer>,
    onItemClick: (Int) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = beers.loadState) {
        if (beers.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (beers.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        if (beers.loadState.refresh is LoadState.NotLoading) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(count = beers.itemCount,
                    key = beers.itemKey { it.id },
                    contentType = beers.itemContentType { "beers" }) { index ->
                    val beer = beers[index]
                    if (beer != null) {
                        BeerItem(
                            beer = beer,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { beerId ->
                                onItemClick(beerId)
                                Log.d("CLICK", "click on beer (${beerId})")
                            }
                        )
                    }
                }
//                item {
//                    if (beers.loadState.append is LoadState.Loading) {
//                        CircularProgressIndicator() // TODO replace it
//                    }
//                }
            }
        }

        AnimatedVisibility(
            visible = beers.loadState.refresh is LoadState.Loading,
            exit = fadeOut(animationSpec = tween(500))
        ) {
            ShimmerRow()
        }

        SmallFloatingActionButton(
            onClick = {  },
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Default.Info, "Small floating action button.")
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    backgroundColor = 0xFF1A191C
)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BeerScreenPreview() {
    BeerAcademyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 12.dp, 0.dp, 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BeerItemDayPreview()
            BeerItemNightPreview()
            BeerItemDayPreview(
                beer = Beer(
                    id = 1,
                    name = "Beer with very long name on bottle",
                    tagline = "This is cool light beer with very long name on bottle",
                    firstBrewed = "07/2023",
                    description = "This is a description for a beer. This is just a next phrase. And this is light beer with very long name on bottle. It is so long than should take more than 3 lines here.",
                    imageUrl = null,
                    foodPairing = emptyList(),
                    brewersTips = ""
                )
            )
        }
    }
}
