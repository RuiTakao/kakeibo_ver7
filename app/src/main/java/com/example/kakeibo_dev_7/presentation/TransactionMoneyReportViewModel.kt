package com.example.kakeibo_dev_7.presentation

import androidx.lifecycle.ViewModel
import com.example.kakeibo_dev_7.domain.model.TransactionMoneyReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionMoneyReportViewModel @Inject constructor(
//    private val transactionMoneyReportUseCase: TransactionMoneyReportUseCase
) : ViewModel() {
    fun get() {
//        transactionMoneyReportUseCase
    }
}