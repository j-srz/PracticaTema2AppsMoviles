package com.example.prueba

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba.ui.theme.PruebaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ShowToast("FUNCION oncreate()")

        enableEdgeToEdge()
        setContent {
            PruebaTheme {
                ViewContainer()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        ShowToast("FUNCION onstart()")
    }

    // IMPLEMENTAR
    // onResume
    override fun onResume() {
        super.onResume()
        ShowToast("FUNCION onresume()")
    }

    // onPause
    override fun onPause() {
        super.onPause()
        ShowToast("FUNCION onpause()")
    }

    // onStop
    override fun onStop() {
        super.onStop()
        ShowToast("FUNCION onstop()")
    }

    // onDestroy
    override fun onDestroy() {
        super.onDestroy()
        ShowToast("FUNCION ondestroy()")
    }

    fun ShowToast(message:String) {
        val toast = Toast.makeText( // Metodo de la clase Toast encargado de contruir y preparar el toast
            this, // Contexto de la actividad
            message,
            Toast.LENGTH_LONG
        )
        toast.show() // Metodo para mostrar el toast
        Log.i(TAG, message)
        // v verbose info
        // d debug info
        // i info
        // w warning
        // e error
        // wtf errs catastroficos
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewContainer() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, // Define donde se mostraran los snackbars
        // el hostState es el controlador de los snackbars
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.principalScreen)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    scrolledContainerColor = Color.White,
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.bottom_app_bar),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        },
        floatingActionButton = {
            val context = LocalContext.current
            FloatingActionButton(onClick = {
                // Toast.makeText(
                //    context,
                //    R.string.home_fab_text,
                //    Toast.LENGTH_LONG
                // ).show()
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Snackbar! <3",
                        actionLabel = "Do something"
                    )
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.home_fab_text)
                )
            }
        }
    ) { innerPadding ->
        ContentView(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ContentView(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableIntStateOf(0) }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.imgprincipal1),
                contentDescription = "Ilustración de Android",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Row(modifier = Modifier.padding(8.dp)) {
                Icon(
                    painter = painterResource(R.drawable.baseline_exposure_plus_1_24),
                    contentDescription = "Star",
                    modifier = Modifier.clickable { counter++ }
                )
                Text(
                    text = counter.toString(),
                     fontSize = 20.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = stringResource(R.string.img_desc),
                fontSize = 24.sp,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.materia),
                fontSize = 24.sp,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.nombre),
                fontSize = 25.sp,
                color = Color.Magenta,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.apellido),
                fontSize = 24.sp,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                item {
                    Image(
                        modifier = Modifier
                            .size(150.dp, 100.dp)
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.img1),
                        contentScale = ContentScale.Crop,
                        contentDescription = "mobileApps"
                    )
                    Image(
                        modifier = Modifier
                            .size(150.dp, 100.dp)
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.img2),
                        contentScale = ContentScale.Crop,
                        contentDescription = "mobileApps"
                    )
                    Image(
                        modifier = Modifier
                            .size(150.dp, 100.dp)
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.img3),
                        contentScale = ContentScale.Crop,
                        contentDescription = "mobileApps"
                    )
                    Image(
                        modifier = Modifier
                            .size(150.dp, 100.dp)
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.img4),
                        contentScale = ContentScale.Crop,
                        contentDescription = "mobileApps"
                    )
                    Image(
                        modifier = Modifier
                            .size(150.dp, 100.dp)
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.img5),
                        contentScale = ContentScale.Crop,
                        contentDescription = "mobileApps"
                    )
                }
            }
        }
    }
}
