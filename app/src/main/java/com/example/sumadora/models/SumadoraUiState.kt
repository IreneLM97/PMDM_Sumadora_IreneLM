package com.example.sumadora.models

/**
 * Clase que representa el estado de la interfaz de usuario de la sumadora.
 *
 * @property currentSuma suma actual que se realiza
 * @property listSumas lista de sumas realizadas por el usuario
 */
data class SumadoraUiState(
    val currentSuma: Suma,
    val listSumas: MutableList<Suma>
)

/** Inicializamos el estado */
val initialState = SumadoraUiState(
    currentSuma = Suma(0, 0, 0),
    listSumas = mutableListOf()
)

