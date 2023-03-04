package ru.yandex.repinanr.binapp.presentation

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


fun hideSoftKeyboard(activity: Activity) {
    if (activity.currentFocus == null) {
        return
    }
    val inputMethodManager: InputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
}