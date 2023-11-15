package com.example.sumadora.models

data class SumadoraUiState(
    val currentSuma: Suma,
    val listSumas: MutableList<Suma>
)

/** Inicializamos el estado */
val initialState = SumadoraUiState(
    currentSuma = Suma(0, 0, 0),
    listSumas = mutableListOf()
)

