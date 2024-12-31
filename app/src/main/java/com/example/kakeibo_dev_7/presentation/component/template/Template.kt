package com.example.kakeibo_dev_7.presentation.component.template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.presentation.component.bottom_bar.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Template(navController: NavController,content: @Composable() ColumnScope.() -> Unit ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "top") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = { BottomBar(navController = navController) },
        content = { padding -> Column(modifier = Modifier.padding(padding), content = content) }
    )
}