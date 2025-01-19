package com.example.kakeibo_dev_7.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kakeibo_dev_7.common.BottomNavigationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    val bottomNavigationStatus = mutableStateOf(BottomNavigationStatus.AddIncomeExpenditure)

}