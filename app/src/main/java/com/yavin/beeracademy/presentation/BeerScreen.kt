package com.yavin.beeracademy.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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

@Composable
fun BeerScreen(
    beers: LazyPagingItems<Beer>
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
        if (beers.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(0.dp, 12.dp, 0.dp, 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = beers.itemCount,
                    key = beers.itemKey { it.id },
                    contentType = beers.itemContentType { "beers" }
                ) { index ->
                    val beer = beers[index]
                    if (beer != null) {
                        BeerItem(beer = beer, modifier = Modifier.fillMaxWidth())
                    }
                }
                item {
                    if (beers.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun BeerScreenPreview() {
    Column(
        modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BeerItemDayPreview()
        BeerItemNightPreview()
    }
}