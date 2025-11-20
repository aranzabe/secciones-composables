package com.example.seccionescomposables

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.seccionescomposables.ui.theme.SeccionesComposablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SeccionesComposablesTheme {
                Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    Seccion1()
                    Seccion2()
                }
            }
        }
    }
}

@Composable
fun Seccion1() {
    Log.d("Fernando", "Recomponiedo la sección 1")
    Text(text = "Sección 1")
    Button(onClick = {
        Log.i("Fernando", "Pulsado el botón 1 de la sección 1")
    }) {
        Text(text = "Botón 1")
    }
}


@Composable
fun Seccion2() {
    var cont by remember { mutableIntStateOf(0) }
    Log.d("Fernando", "Recomponiedo la sección 2")
    Text(text = "Sección 2")
    Button(onClick = {
        Log.w("Fernando","Pulsado el botón 2 de la sección 1")
        cont++
    }) {
        Text(text = "Botón 2")
    }
    Text(text = "Valor del contador: ${cont}")
}
