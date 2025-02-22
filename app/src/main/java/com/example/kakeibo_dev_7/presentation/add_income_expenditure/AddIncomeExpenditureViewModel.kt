package com.example.kakeibo_dev_7.presentation.add_income_expenditure

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.kakeibo_dev_7.common.IncomeExpenditureColor
import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus
import com.example.kakeibo_dev_7.domain.model.ExpenditureReport
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddIncomeExpenditureViewModel : ViewModel() {
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

    private val _incomeExpenditure = MutableStateFlow<List<ExpenditureReport>>(emptyList())

    val incomeExpenditure: StateFlow<List<ExpenditureReport>> = _incomeExpenditure.asStateFlow()

    private val db = FirebaseFirestore.getInstance()

    init {
        db.collection("income_expenditure")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
//
                val incomeExpenditureList = mutableListOf<ExpenditureReport>()
//
                for (doc in snapshot!!) {
                    doc.toObject(ExpenditureReport::class.java).let {
                        incomeExpenditureList.add(it)
//                    }
                    }
//
                    _incomeExpenditure.value = incomeExpenditureList
                }
            }
    }
    companion object {
        private const val TAG = "AddIncomeExpenditureViewModel"
    }
}