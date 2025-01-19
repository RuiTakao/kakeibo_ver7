package com.example.kakeibo_dev_7.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.AppLaunchChecker
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.kakeibo_dev_7.data.AppDataBase
import com.example.kakeibo_dev_7.domain.model.User
import com.example.kakeibo_dev_7.domain.repository.UserDao
import com.example.kakeibo_dev_7.presentation.add_income_expenditure.AddIncomeExpenditureScreen
import com.example.kakeibo_dev_7.presentation.ui.theme.Kakeibo_dev_7Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (!AppLaunchChecker.hasStartedFromLauncher(applicationContext)) {
                val viewModel : CreateUserViewModel = hiltViewModel()
                viewModel.createUser()
            }

            Kakeibo_dev_7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.AddIncomeExpenditureScreen.route
                    ) {

                        composable(route = ScreenRoute.AddIncomeExpenditureScreen.route) {
                            AddIncomeExpenditureScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}