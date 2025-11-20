# Secciones comosables

# **Recomposición**

El código dado muestra dos secciones independientes en una pantalla. Esto permite ver cómo **cada composable se recompone de forma aislada**, algo fundamental en Jetpack Compose.

## **1. Estructura general del proyecto**

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SeccionesComposablesTheme {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Seccion1()
                    Seccion2()
                }
            }
        }
    }
}
```

### ✔️ Puntos clave:

- **setContent {}** → Es donde se define la interfaz usando composables.
- Dentro del **Column**, se llaman dos funciones composables: `Seccion1()` y `Seccion2()`.
- Compose renderiza estos composables y los vuelve a pintar cuando su estado cambia.

---

## 2. Sección 1: un composable sin estado

```kotlin
@Composable
fun Seccion1() {
    Log.e("Fernando", "Recomponiedo la sección 1")
    Text(text = "Sección 1")
    Button(onClick = {
        Log.e("Fernando", "Pulsado el botón 1 de la sección 1")
    }) {
        Text(text = "Botón 1")
    }
}
```

### ✔️ Conceptos importantes:

- Esta sección **no usa estado**, así que *nunca se recompone* a menos que:
  - Cambie su entrada (parámetros)
  - Se recomponga el padre (MainActivity → Column)
- Como no cambia nada en ella, **al pulsar el botón NO se vuelve a pintar**.
- El log sirve para comprobarlo.

👉 **Conclusión:**

`Seccion1()` es estática y no provoca recomposiciones porque **no contiene estado**.

---

## 3. Sección 2: manejo de estado con remember

```kotlin
@Composable
fun Seccion2() {
    var cont by remember { mutableIntStateOf(0) }
    Log.e("Fernando", "Recomponiedo la sección 2")

    Text(text = "Sección 2")
    Button(onClick = {
        Log.e("Fernando","Pulsado el botón 2 de la sección 1")
        cont++
    }) {
        Text(text = "Botón 2")
    }
    Text(text = "Valor del contador: ${cont}")
}
```

### ✔️ ¿Qué ocurre aquí?

- `cont` es un **estado observable** de Compose.
- `remember { ... }` hace que Compose **recuerde su valor entre recomposiciones**.
- Cada vez que `cont++` cambia:
  - Compose detecta el cambio
  - **Recompone SOLO Seccion2**, no la pantalla completa

👉 En los logs verás cómo `Seccion2` se recompone cada vez que pulsas el botón.

---

# **4. ¿Qué es la recomposición en Jetpack Compose?**

La **recomposición** es el proceso en el que Compose vuelve a ejecutar un composable para actualizar la interfaz cuando un estado cambia.

### ⚡ Importante:

- NO redibuja la pantalla completa.
- NO destruye y recrea vistas como en XML.
- Compose solo vuelve a ejecutar **los composables afectados por cambios de estado**.

Es uno de los motivos por los que Compose es más eficiente que las vistas tradicionales.

---

# **5. Diferencias claves entre Seccion1 y Seccion2**

| Sección | Usa estado | ¿Se recompone al pulsar? | ¿Por qué? |
| --- | --- | --- | --- |
| **Seccion1** | ❌ No | ❌ No | No cambia nada observable |
| **Seccion2** | ✔️ Sí (`cont`) | ✔️ Sí | El estado `cont` ha cambiado |

# Salida por consola:
```bash
Pulsado el botón 1 de la sección 1
Pulsado el botón 2 de la sección 1
Recomponiedo la sección 2
Pulsado el botón 1 de la sección 1
Pulsado el botón 2 de la sección 1
Recomponiedo la sección 2
Pulsado el botón 2 de la sección 1
Recomponiedo la sección 2
Pulsado el botón 2 de la sección 1
Recomponiedo la sección 2
Pulsado el botón 1 de la sección 1
````
