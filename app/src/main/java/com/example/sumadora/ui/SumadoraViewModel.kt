package com.example.sumadora.ui

import androidx.lifecycle.ViewModel
import com.example.sumadora.models.Suma
import com.example.sumadora.models.SumadoraUiState
import com.example.sumadora.models.initialState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel que permite mantener el estado de la aplicación.
 */
class SumadoraViewModel : ViewModel() {

    // Variable que representa la información de la interfaz de usuario
    private val _uiState = MutableStateFlow(initialState)

    // Variable que permite actualizar la información la interfaz de usuario
    val uiState: StateFlow<SumadoraUiState> = _uiState

    /**
     * Actualiza la suma actual.
     *
     * @param currentSuma suma actual
     */
    fun updateCurrentSuma(
        currentSuma: Suma
    ) {
        _uiState.update {
            it.copy(
                currentSuma = currentSuma
            )
        }
    }

    /**
     * Actualiza la lista de sumas realizadas por el usuario.
     *
     * @param currentSuma suma actual realizada por el usuario
     */
    fun updateListSumas(
        currentSuma: Suma
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                listSumas = currentState.listSumas.toMutableList().apply { add(currentSuma) }
            )
        }
    }
}