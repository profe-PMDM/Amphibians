package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.data.NetworkAmphibiansRepository
import com.example.amphibians.network.Amphibian
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val photos: List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}
class AmphibiansViewModel: ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getAmphibians()
    }

    /**
     * Gets Amphibians information from the Amphibians API Retrofit service and updates the
     */
    private fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = try {
                val amphibiansRepository = NetworkAmphibiansRepository()
                AmphibiansUiState.Success(amphibiansRepository.getAmphibians())
            } catch (e: IOException){
                AmphibiansUiState.Error
            }
        }
    }
}