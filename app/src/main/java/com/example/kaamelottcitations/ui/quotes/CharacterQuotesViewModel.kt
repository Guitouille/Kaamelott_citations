package com.example.kaamelottcitations.ui.quotes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaamelottcitations.data.KaamelottQuotesEntity
import com.example.kaamelottcitations.data.KaamelottQuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterQuotesViewModel @Inject constructor(private val kaamelottQuotesRepository: KaamelottQuotesRepository) :
    ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    fun fetchCharacterQuotesByBook(bookNumber: Int, characterName: String) {
        Timber.d("book $bookNumber character $characterName")
        viewModelScope.launch {
            kaamelottQuotesRepository.fetchCharacterQuotesByBook(bookNumber, characterName).fold(
                onSuccess = {
                    uiState = uiState.copy(quotes= it.toQuotesModel(), loading = false)
                    Timber.d("SUCCESS")
                },
                onFailure = {
                    Timber.d("ERROR")
                }
            )
        }
    }
}

data class UiState(
    var quotes: QuotesModel = QuotesModel(),
    var loading: Boolean = true
)