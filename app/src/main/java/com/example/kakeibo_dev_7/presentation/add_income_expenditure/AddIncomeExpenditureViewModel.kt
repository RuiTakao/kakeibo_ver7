package com.example.kakeibo_dev_7.presentation.add_income_expenditure

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.kakeibo_dev_7.common.IncomeExpenditureColor
import com.example.kakeibo_dev_7.common.IncomeExpenditureStatus
import com.example.kakeibo_dev_7.domain.model.Category
import com.example.kakeibo_dev_7.domain.model.ExpenditureReport
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
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

    private val _category = MutableStateFlow<List<Category>>(emptyList())

    val category: StateFlow<List<Category>> = _category.asStateFlow()

    private val db = FirebaseFirestore.getInstance()

    init {
        db.collection("income_expenditure")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                val incomeExpenditureList = mutableListOf<ExpenditureReport>()
                for (doc in snapshot!!) {
                    doc.toObject(ExpenditureReport::class.java).let {
                        incomeExpenditureList.add(it)
                    }
                    _incomeExpenditure.value = incomeExpenditureList
                }
            }

        db.collection("category")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                val categoryList = mutableListOf<Category>()

                for (doc in snapshot!!) {
                    doc.toObject(Category::class.java).let {
                        categoryList.add(it)
                    }

                    _category.value = categoryList
                }
            }
    }

    // 各プロパティ

    // String型で日付保存で期間検索できるか
    // できなければDate型で保存し、フロントで整形
    var payDate by mutableStateOf("")
    var categoryId by mutableStateOf("")
    var content by mutableStateOf("")
    var price by mutableStateOf("")
    fun create() {
        val data = hashMapOf(
            "payDate" to payDate,
            "price" to price,
            "categoryId" to categoryId,
            "content" to content,
            "kind" to "expenditure",
            "userId" to "",
            "created" to "20220222",
            "updated" to "20220222",
        )
        db.collection("income_expenditure")
            .add(data)
            .addOnSuccessListener {
                Log.d("success", it.id)
            }
            .addOnFailureListener {
                Log.w("error", it)
            }
    }
    companion object {
        private const val TAG = "AddIncomeExpenditureViewModel"
    }
}