package com.example.kakeibo_dev_7.presentation.add_income_expenditure

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.kakeibo_dev_7.common.IncomeExpenditureColor
import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddIncomeExpenditureViewModel @Inject constructor(): ViewModel() {
    val incomeExpenditureStatus = mutableStateOf(IncomeExpenditureStatus.Expenditure)

    val incomeStatus = incomeExpenditureStatus.value === IncomeExpenditureStatus.Income

    val expenditureStatus = incomeExpenditureStatus.value === IncomeExpenditureStatus.Expenditure

    val paymentInputLabel = when (incomeExpenditureStatus.value) {
        IncomeExpenditureStatus.Income -> "収入"
        IncomeExpenditureStatus.Expenditure -> "支出"
    }

    var saveButtonColor = Color(
        when (incomeExpenditureStatus.value) {
            IncomeExpenditureStatus.Income -> IncomeExpenditureColor.incomeColor
            IncomeExpenditureStatus.Expenditure -> IncomeExpenditureColor.expenditureColor
        }
    )
}