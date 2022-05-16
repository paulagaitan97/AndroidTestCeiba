package com.gaitan.dev.androidtestceiba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gaitan.dev.androidtestceiba.ui.theme.AndroidTestCeibaTheme
import com.gaitan.dev.core.navigation.Screen
import com.gaitan.dev.core_ui.utils.TextBasic
import com.gaitan.dev.user_presentation.screen.ListUserScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTestCeibaTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                var canPop by remember {
                    mutableStateOf(false)
                }

                navController.addOnDestinationChangedListener{controller, _, _ ->
                    canPop = controller.previousBackStackEntry != null
                }
                val navigationIcon: (@Composable () -> Unit )? =
                    if(canPop) {
                        {
                            IconButton(onClick = {
                                navController.popBackStack()

                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    } else{
                        null
                    }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = { TextBasic(text = getString(R.string.text_title_app_bar)) },
                            navigationIcon = navigationIcon
                        )
                    }

                ) {

                    NavHost(
                        navController = navController,
                        startDestination = Screen.AllUserScreen.route
                    ) {
                        composable(route = Screen.AllUserScreen.route) {
                            ListUserScreen(
                                navController = navController,
                                scaffoldState = scaffoldState
                            )
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidTestCeibaTheme {
        Greeting("Android")
    }
}