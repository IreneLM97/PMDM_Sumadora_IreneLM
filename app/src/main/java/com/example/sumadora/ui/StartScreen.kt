package com.example.sumadora.ui

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sumadora.R
import com.example.sumadora.models.Suma

@Composable
fun StartScreen(
    viewModel: SumadoraViewModel = viewModel(),
    onClickButton: (Suma) -> Unit = {}
) {
    var n1Input by rememberSaveable  { mutableStateOf("") }
    var n2Input by rememberSaveable  { mutableStateOf("") }

    val n1 = n1Input.toIntOrNull() ?: 0
    val n2 = n2Input.toIntOrNull() ?: 0

    // Diseño de la página
    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.start_message),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        EditNumberField(
            label = R.string.primer_numero,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = n1Input,
            onValueChanged = { n1Input = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        EditNumberField(
            label = R.string.segundo_numero,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = n2Input,
            onValueChanged = { n2Input = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        Spacer(Modifier.height(5.dp))

        // Botón para enviar a otra aplicación
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 70.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    // Crear una nueva suma a partir de los valores introducidos
                    val currentSuma = Suma(n1, n2, n1 + n2)

                    // Actualizar el estado utilizando el ViewModel
                    viewModel.updateCurrentSuma(currentSuma)
                    viewModel.updateListSumas(currentSuma)

                    // Limpiar los campos
                    n1Input = ""
                    n2Input = ""

                    onClickButton(currentSuma)
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.my_darkest_purple)),
            ) {
                Text(
                    fontSize = 20.sp,
                    text = stringResource(R.string.sumar)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen()
}