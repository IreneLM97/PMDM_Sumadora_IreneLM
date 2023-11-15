package com.example.sumadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sumadora.ui.theme.SumadoraTheme

/**
 * Clase principal de la aplicación que representa la actividad principal.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SumadoraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SumadoraApp()
                }
            }
        }
    }
}

/**
 * Vista previa de la aplicación.
 */
@Preview(showBackground = true)
@Composable
fun SumadoraAppPreview() {
    SumadoraTheme {
        SumadoraApp()
    }
}