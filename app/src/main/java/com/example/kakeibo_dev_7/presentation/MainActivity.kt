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
import com.example.kakeibo_dev_7.presentation.add_income_expenditure.AddIncomeExpenditureScreen
import com.example.kakeibo_dev_7.presentation.expenditure_report_screen.ExpenditureReportScreen
import com.example.kakeibo_dev_7.presentation.income_report_screen.IncomeReportScreen
import com.example.kakeibo_dev_7.presentation.ui.theme.Kakeibo_dev_7Theme
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val viewModel : CreateUserViewModel = hiltViewModel()
//            viewModel.createUser()
//            if (!AppLaunchChecker.hasStartedFromLauncher(applicationContext)) {
//                val viewModel : CreateUserViewModel = hiltViewModel()
//                viewModel.createUser()
//            }
            val data = hashMapOf(
                "name" to "11111"
            )

            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                .add(data)

            Kakeibo_dev_7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    val navigationViewModel: NavigationViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.AddIncomeExpenditureScreen.route
                    ) {

                        composable(route = ScreenRoute.AddIncomeExpenditureScreen.route) {
                            AddIncomeExpenditureScreen(navController = navController, navigationViewModel = navigationViewModel)
                        }

                        composable(route = ScreenRoute.ExpenditureReportScreen.route) {
                            ExpenditureReportScreen(navController = navController, navigationViewModel = navigationViewModel)
                        }

                        composable(route = ScreenRoute.IncomeReportScreen.route) {
                            IncomeReportScreen(navController = navController, navigationViewModel = navigationViewModel)
                        }

                        composable(route = ScreenRoute.TransactionMoneyReportScreen.route) {
                            TransactionMoneyReportScreen(navController = navController, navigationViewModel = navigationViewModel)
                        }
                    }
                }
            }
        }
    }
}