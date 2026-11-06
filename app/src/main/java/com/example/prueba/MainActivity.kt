package com.example.prueba

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.prueba.ui.theme.PruebaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    private var keepSplash = true



    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplash }

        lifecycleScope.launch {
            delay(3000.milliseconds)
            keepSplash = false
        }



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

    // onRestart
    override fun onRestart() {
        super.onRestart()
        ShowToast("FUNCION onrestart()")
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

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
                },
                // Esto noss paso el profe
                // INTENTS
                // Explicitos: Dentro de la app
                // Implicitos: El sistema resulve en apps ext
                // Tiene q tener acc al localcontex
                actions = {


                    IconButton (onClick = { openWebScreen(context) } ) {
                        Icon(
                            imageVector = Icons.Default.AddLink,
                            contentDescription = "Abrir un navegador externo",
                            tint = Color.White
                        )

                    }

                    // Aqui tenemos que abrir la secondActivityw
                    IconButton(onClick = {
                        val intent = Intent(context, SecondActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.NavigateNext,
                            contentDescription = "Siguiente pagina",
                            tint = Color.White
                        )
                    }
                DropDownMenu()
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
fun DropDownMenu() {
    var expanded by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.options_menu),
                tint= Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            //
            DropdownMenuItem(
                text = { Text(stringResource(R.string.calendar)) },
                onClick = {
                    expanded = false
                    // Acccion oara calendario
                    openCalendar(context)


                },
                leadingIcon = {
                    Icon(Icons.Default.Event, contentDescription = null)
                }
            )

            DropdownMenuItem(
                text = { Text(stringResource(R.string.alarm)) },
                onClick = {
                    expanded = false
                    // Acción para Alarma
                    openAlarm(context)
                },
                leadingIcon = {
                    Icon(Icons.Default.Watch, contentDescription = null)
                }
            )

            HorizontalDivider()

            DropdownMenuItem(
                text = { Text(stringResource(R.string.file)) },
                onClick = {
                    expanded = false
                    // Acción para Files
                    openFiles(context)
                },
                leadingIcon = {
                    Icon(Icons.Default.FileDownload, contentDescription = null)
                }
            )
        }
    }
}

// YO 4 Calendario, Alarma, Archivos y descargas
//

fun openWebScreen(context: Context) {


    try {
        val url = Uri.parse("https://aguascalientes.tecnm.mx/")
        val intent = Intent(Intent.ACTION_VIEW, url)
        context.startActivity(intent)

    } catch (e: Exception) {

        Log.e("MainActivity", "Fallo...")

    }
}

fun openCalendar(context: Context) {
    try {

        val intent = Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).apply {
            val beginTime: Calendar = Calendar.getInstance().apply {
                set(2021, 0, 23, 7, 30)
            }
            val endTime = Calendar.getInstance().apply {
                set(2021, 0, 23, 10, 30)
            }
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
            putExtra(CalendarContract.Events.TITLE, "Evento importante")
            putExtra(CalendarContract.Events.EVENT_LOCATION, "Tijuana")
        }
        context.startActivity(intent)




    } catch (e: Exception) {
        Log.e("MainActivity", "Fallo en abrir calendario" + e.message )
    }
}

fun openAlarm(context: Context) {
    try {

        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Despiertaaaaaaaaaaaaaa")
            putExtra(AlarmClock.EXTRA_HOUR, 7)
            putExtra(AlarmClock.EXTRA_MINUTES, 30)
        }
        context.startActivity(intent)




    } catch (e: Exception) {
        Log.e("MainActivity", "Fallo en abrir alarma" + e.message)
    }
}

fun openFiles(context: Context) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI, "*/*")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(intent)

    } catch (e: Exception) {
        Log.e("MainActivity", "Fallo en abrir alarma" + e.message)
    }
}






@Composable
fun ContentView(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableIntStateOf(0) }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.secundary))
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
            Row(modifier = Modifier.background(Color.Black).padding(10.dp)) {
                Icon(
                    painter = painterResource(R.drawable.baseline_exposure_plus_1_24),
                    contentDescription = "Plus",
                    tint = Color.White,
                    modifier = Modifier.clickable { counter++ }
                )
                Text(
                    text = counter.toString(),
                     fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = stringResource(R.string.img_desc),
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.materia),
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.nombre),
                fontSize = 25.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.apellido),
                fontSize = 24.sp,
                color = Color.Gray,
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
                }
                item {
                    Image(
                        modifier = Modifier
                            .size(150.dp, 100.dp)
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.img2),
                        contentScale = ContentScale.Crop,
                        contentDescription = "mobileApps"
                    )
                }
                item {
                    Image(
                        modifier = Modifier
                            .size(150.dp, 100.dp)
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.img3),
                        contentScale = ContentScale.Crop,
                        contentDescription = "mobileApps"
                    )
                }
                item {
                    Image(
                        modifier = Modifier
                            .size(150.dp, 100.dp)
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.img4),
                        contentScale = ContentScale.Crop,
                        contentDescription = "mobileApps"
                    )
                }
                item {
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
