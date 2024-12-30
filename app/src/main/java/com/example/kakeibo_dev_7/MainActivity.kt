package com.example.kakeibo_dev_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.sharp.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kakeibo_dev_7.ui.theme.Kakeibo_dev_7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kakeibo_dev_7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = ScreenRoute.Edit.route) {

                        composable(route = ScreenRoute.Edit.route) {
                            Greeting("Android")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var isVisible by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "top") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.padding(0.dp).height(70.dp), containerColor = Color.Yellow) {

//            NavigationBar(
//                modifier = Modifier.fillMaxWidth(),
////                    containerColor = Color.Blue
//            ) {
                

                
                
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        isVisible = !isVisible
                    }, icon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "編集",
                            modifier = Modifier.background(Color.Transparent)
                        )
                    },
                    enabled = false
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        isVisible = !isVisible
                    },
                    icon = { Text(text = "支出") })
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = { Text(text = "収入") })
                NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
                    Icon(
                        imageVector = Icons.Filled.ReceiptLong,
                        contentDescription = "明細"
                    )
                })
                NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "設定"
                    )
                })

//            }

            }

        },
        floatingActionButton = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "w")
            }

        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = name)
        }

        if (isVisible) {
            AlertDialog(onDismissRequest = { isVisible = !isVisible }) {
                Box(modifier = modifier.background(Color.White)) {
                    Text(text = "eeeeeee")
                }

            }
        }
    }
}

@Composable
fun IncomeExpense() {
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Kakeibo_dev_7Theme {
        Greeting("Android")
    }
}