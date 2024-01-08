package com.yavin.beeracademy.presentation

import androidx.lifecycle.ViewModel
import com.yavin.beeracademy.data.local.BeerDatabase
import com.yavin.beeracademy.data.mappers.toBeer
import com.yavin.beeracademy.domain.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerDetailsViewModel @Inject constructor(
    private val beerDb: BeerDatabase
) : ViewModel() {

    suspend fun getBeerById(beerId: Int): Beer {
        return beerDb.dao.getBeerById(beerId).toBeer()
    }

}