package com.example.sumadora.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sumadora.R
import com.example.sumadora.models.Suma
import com.example.sumadora.models.SumadoraUiState

/**
 * Función que representa la pantalla de resumen de la aplicación.
 *
 * @param sumadoraUiState estado actual de la interfaz de usuario
 * @param onBackClick función para manejar el click en el botón de retroceso
 */
@Composable
fun SummaryScreen(
    sumadoraUiState: SumadoraUiState,
    onBackClick: () -> Unit = {}
) {
    // Diseño de la página
    Column(
        modifier = Modifier
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Última suma realizada
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(colorResource(id = R.color.my_normal_purple))
        ) {
            Text(
                text = stringResource(
                    R.string.operation,
                    sumadoraUiState.currentSuma.sumando1,
                    sumadoraUiState.currentSuma.sumando2,
                    sumadoraUiState.currentSuma.resultado
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(Modifier.height(5.dp))

        // Historial de operaciones
        Text(
            text = stringResource(R.string.summary_message),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        // Listado de operaciones
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.my_light_purple))
                .padding(8.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(bottom = 8.dp),
            ) {
                items(sumadoraUiState.listSumas) { suma ->
                    Text(
                        text = stringResource(
                            R.string.operation,
                            suma.sumando1,
                            suma.sumando2,
                            suma.resultado
                        ),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }

        // Botón volver atrás
        Row(
            modifier = Modifier
                .padding(22.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onBackClick()  // este evento se define en SumadoraScreen
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.my_darkest_purple)),
            ) {
                Text(stringResource(R.string.back_button))
            }
        }
    }
}

/**
 * Función para previsualizar la pantalla de resumen de la aplicación.
 */
@Preview
@Composable
fun SummaryScreenPreview() {
    SummaryScreen(
        sumadoraUiState = SumadoraUiState(
            currentSuma = Suma(1, 2, 3),
            listSumas = mutableListOf(
                Suma(1, 2, 3),
                Suma(2, 3, 5)
            )
        )
    )
}