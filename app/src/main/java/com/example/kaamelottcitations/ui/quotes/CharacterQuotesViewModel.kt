package com.example.kaamelottcitations.ui.quotes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaamelottcitations.data.kaamelottquotes.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterQuotesViewModel @Inject constructor(private val quotesRepository: QuotesRepository) :
    ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(var quotes: QuotesModel = QuotesModel()) : UiState()
        object Error : UiState()
    }

    val uiState: State<UiState>
        get() = _uiState
    private val _uiState = mutableStateOf<UiState>(UiState.Loading)

    fun fetchCharacterQuotesByBook(bookNumber: Int, characterName: String) {
        Timber.d("book $bookNumber character $characterName")
        viewModelScope.launch(Dispatchers.IO) {
            quotesRepository.fetchCharacterQuotesByBook(bookNumber, characterName).fold(
                onSuccess = {
                    withContext(Dispatchers.Main) {
                        _uiState.value = UiState.Success(it.toQuotesModel())
                    }
                    Timber.d("SUCCESS")
                },
                onFailure = {
                    Timber.d("ERROR")
                }
            )
        }
    }
}
