package com.example.kakeibo_dev_7.presentation.expenditure_report_screen

import androidx.lifecycle.ViewModel
import com.example.kakeibo_dev_7.domain.model.Category
import com.example.kakeibo_dev_7.domain.model.ExpenditureReport
import com.example.kakeibo_dev_7.domain.model.IncomeExpenditure
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExpenditureReportViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _expenditureReport = MutableStateFlow<List<ExpenditureReport>>(emptyList())

    val expenditureReport: StateFlow<List<ExpenditureReport>> = _expenditureReport.asStateFlow()

    init {
        db.collection("category")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                val expenditureReportList = mutableListOf<ExpenditureReport>()

                for (doc in snapshot!!) {
                    doc.toObject(Category::class.java).let {
                        var paymentPriceSum = 0
                        var paymentCount = 0

                        db.collection("income_expenditure").whereEqualTo("categoryId", it.id)
                            .addSnapshotListener addSnapshotListener@{ snapshot, _ ->

                                for (doc in snapshot!!) {
                                    doc.toObject(IncomeExpenditure::class.java).let { expenditure ->
                                        paymentCount++
                                        paymentPriceSum += expenditure.price.toInt()
                                    }
                                }

                                val data = ExpenditureReport(
                                    categoryId = it.id,
                                    categoryName = it.name,
                                    paymentPriceSum = paymentPriceSum,
                                    paymentCount = paymentCount
                                )

                                expenditureReportList.add(data)
                                _expenditureReport.value = expenditureReportList
                            }
                    }
                }
            }
    }
}