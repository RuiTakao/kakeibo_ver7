package com.example.kakeibo_dev_7.presentation.expenditure_report_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kakeibo_dev_7.common.IncomeExpenditureColor
import com.example.kakeibo_dev_7.domain.model.ExpenditureReport
import com.example.kakeibo_dev_7.presentation.NavigationViewModel
import com.example.kakeibo_dev_7.presentation.components.layouts.Default

@Composable
fun ExpenditureReportScreen(
    navController: NavController,
    navigationViewModel: NavigationViewModel
) {

    Default(navController = navController, viewModel = navigationViewModel) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            color = Color.Gray,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 1.dp.toPx()
                        )
                    }
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .drawBehind {
                            drawLine(
                                color = Color(IncomeExpenditureColor.expenditureColor),
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 2.dp.toPx()
                            )
                        }
                ) {
                    Text(
                        text = "支出項目",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(IncomeExpenditureColor.expenditureColor),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = Color.Transparent,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 2.dp.toPx()
                            )
                        }
                ) {
                    Text(
                        text = "支出グラフ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(top = 16.dp)
            ) {

                Column(horizontalAlignment = Alignment.End) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "支出期間",
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(text = "${"2024/12/21"} ～ ${"2024/12/28"}")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "支出合計 ￥${"10,000"}")

                }

                Column(
                    modifier = Modifier.padding(top = 16.dp)
                ) {

                    val mock1 = ExpenditureReport(
                        id = 1,
                        categoryName = "🍙 食費",
                        paymentCount = 3,
                        paymentPriceSum = 5000
                    )

                    val mock2 = ExpenditureReport(
                        id = 2,
                        categoryName = "🛍 生活費",
                        paymentCount = 1,
                        paymentPriceSum = 5000
                    )

                    val expenditureReportList: List<ExpenditureReport> = listOf(mock1, mock2)

                    LazyColumn {
                        itemsIndexed(expenditureReportList) { index, report ->
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = if (index != 0) 8.dp else 0.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                                    .padding(vertical = 4.dp)
                                    .padding(start = 16.dp)
                                    .padding(end = 4.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = report.categoryName,
                                    fontWeight = FontWeight.Bold
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.End,
                                        modifier = Modifier.padding(end = 8.dp)
                                    ) {
                                        Text(text = "￥${report.paymentPriceSum}")
                                        Text(
                                            text = "支出回数：${report.paymentCount}回",
                                            fontSize = 12.sp
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.Default.ArrowForwardIos,
                                        contentDescription = "詳細へ"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}