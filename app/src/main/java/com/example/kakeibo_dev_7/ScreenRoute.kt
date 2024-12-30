package com.example.kakeibo_dev_7

 sealed class ScreenRoute(val route: String) {
     object Edit : ScreenRoute("edit")
}