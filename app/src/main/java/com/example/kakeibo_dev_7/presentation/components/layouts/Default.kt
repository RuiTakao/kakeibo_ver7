package com.example.kakeibo_dev_7.presentation.components.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyYen
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Straight
import androidx.compose.material.icons.filled.TrendingFlat
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Default(
    navController: NavController,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "収支入力")
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .padding(0.dp)
                    .height(70.dp),
                containerColor = Color(0xFFEFEFEF)
            ) {

                NavigationBarItem(
                    selected = false,
                    onClick = {}, icon = {
                        Column {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "編集",
                                tint = Color(0xFFFD7E00)
                            )
                            Text(
                                text = "編集",
                                fontSize = 14.sp,
                                color = Color(0xFFFD7E00)
                            )
                        }

                    },
                    enabled = false
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.expenditure_icon),
                            contentDescription = "支出",
                            colorFilter = ColorFilter.tint(
                                Color.Gray
                            )
                        )

                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.income_icon),
                            contentDescription = "支出",
                            colorFilter = ColorFilter.tint(
                                Color.Gray
                            )
                        )

                    }
                )
                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(
                        imageVector = Icons.Filled.ReceiptLong,
                        contentDescription = "明細",
                        tint = Color.Gray
                    )
                })
                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "設定",
                        tint = Color.Gray
                    )
                })
            }
        },
        content = { content(it) }
    )
}