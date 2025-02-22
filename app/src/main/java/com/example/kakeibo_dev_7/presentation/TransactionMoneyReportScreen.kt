package com.example.kakeibo_dev_7.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kakeibo_dev_7.common.IncomeExpenditureColor
import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus
import com.example.kakeibo_dev_7.domain.model.TransactionMoney
import com.example.kakeibo_dev_7.domain.model.TransactionMoneyReport
import com.example.kakeibo_dev_7.domain.model.TransactionMoneyReportUseCase
import com.example.kakeibo_dev_7.presentation.components.layouts.Default

@Composable
fun TransactionMoneyReportScreen(
    navController: NavController,
    viewModel: TransactionMoneyReportViewModel = hiltViewModel(),
    navigationViewModel: NavigationViewModel
) {

    viewModel.get()


    val transactionMoneyReportList: List<TransactionMoneyReport> = useCase()

    Default(navController = navController, viewModel = navigationViewModel) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "前へ",
                        modifier = Modifier.size(10.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "日付")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "2024/12")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "次へ",
                        modifier = Modifier.size(10.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "すべて")
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "カテゴリー選択"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            transactionMoneyReportList.forEachIndexed() { index, item ->
                Column(
                    modifier = Modifier.padding(top = if (index != 0) 16.dp else 0.dp)
                ) {
                    Text(
                        text = item.transactionMoneyDate,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider(thickness = 1.dp, color = Color.Black)
                    LazyColumn() {
                        items(item.transactionMoneyList) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                                    .padding(vertical = 4.dp)
                            ) {
                                Column {
                                    Text(text = it.content)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = it.categoryName,
                                        fontSize = 14.sp
                                    )
                                }
                                Row {
                                    Text(
                                        text =
                                        "${
                                            when (it.transactionKind) {
                                                IncomeExpenditureStatus.Income -> "+"
                                                IncomeExpenditureStatus.Expenditure -> "-"
                                            }
                                        }${it.amount}",
                                        color = Color(
                                            when (it.transactionKind) {
                                                IncomeExpenditureStatus.Income -> IncomeExpenditureColor.incomeColor
                                                IncomeExpenditureStatus.Expenditure -> IncomeExpenditureColor.expenditureColor
                                            }
                                        )
                                    )
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = "詳細"
                                    )
                                }
                            }
                            Divider(thickness = 1.dp, color = Color.Black)
                        }
                    }
                }
            }
        }
    }

}

fun useCase(): List<TransactionMoneyReport> {
    val mock1 = TransactionMoney(
        id = 1,
        content = "夜食",
        amount = 1000,
        transactionMoneyDate = "2024/12/21",
        transactionKind = IncomeExpenditureStatus.Expenditure,
        categoryId = 1,
        categoryName = "食費"
    )
    val mock2 = TransactionMoney(
        id = 2,
        content = "昼ご飯",
        amount = 1500,
        transactionMoneyDate = "2024/12/21",
        transactionKind = IncomeExpenditureStatus.Expenditure,
        categoryId = 1,
        categoryName = "食費"
    )
    val mock3 = TransactionMoney(
        id = 3,
        content = "12月給与",
        amount = 200000,
        transactionMoneyDate = "2024/12/21",
        transactionKind = IncomeExpenditureStatus.Income,
        categoryId = 1,
        categoryName = "給与"
    )

    val mockList1 = TransactionMoneyReport(
        id = 1,
        transactionMoneyDate = "2024/12/21",
        transactionMoneyList = listOf(mock1, mock2, mock3)
    )

    val mock4 = TransactionMoney(
        id = 1,
        content = "夜食",
        amount = 900,
        transactionMoneyDate = "2024/12/24",
        transactionKind = IncomeExpenditureStatus.Expenditure,
        categoryId = 1,
        categoryName = "食費"
    )
    val mock5 = TransactionMoney(
        id = 2,
        content = "昼ご飯",
        amount = 1100,
        transactionMoneyDate = "2024/12/24",
        transactionKind = IncomeExpenditureStatus.Expenditure,
        categoryId = 1,
        categoryName = "食費"
    )

    val mockList2 = TransactionMoneyReport(
        id = 2,
        transactionMoneyDate = "2024/12/24",
        transactionMoneyList = listOf(mock4, mock5)
    )

    return listOf(mockList1, mockList2)
}

@Preview
@Composable
fun TransactionMoneyReportScreenPreview() {
//    val navController = rememberNavController()
//    val navigationViewModel: NavigationViewModel = hiltViewModel()
//    TransactionMoneyReportScreen(navController, navigationViewModel)
}