package com.example.sumadora

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sumadora.ui.SumadoraViewModel
import com.example.sumadora.ui.StartScreen
import com.example.sumadora.ui.SummaryScreen

/**
 * Enumeración que define las posibles pantallas de la aplicación.
 */
enum class SumadoraScreen {
    Start,  // pantalla que muestra la operación actual
    Summary // pantalla que muestra un resumen de las operaciones
}

/**
 * Función que representa la estructura principal de la aplicación.
 *
 * @param viewModel ViewModel que gestiona el estado de la aplicación
 * @param navController controlador de navegación para gestionar la navegación entre pantallas
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SumadoraApp(
    viewModel: SumadoraViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        // Barra personalizada
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.sumadora))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(R.color.my_dark_purple)
                )
            )
        }
    ) { innerPadding ->
        // Variable de estado
        val uiState by viewModel.uiState.collectAsState()

        // Navegación
        NavHost(
            navController = navController,
            startDestination = SumadoraScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = SumadoraScreen.Start.name) {
                StartScreen(
                    viewModel = viewModel,
                    onClickButton = {
                        navController.navigate(SumadoraScreen.Summary.name)  // navegamos a pantalla de resumen
                    }
                )
            }

            composable(route = SumadoraScreen.Summary.name) {
                SummaryScreen(
                    sumadoraUiState = uiState,
                    onBackClick = {
                        navController.popBackStack()  // retroceder en la pila de navegación
                    }
                )
            }
        }
    }
}