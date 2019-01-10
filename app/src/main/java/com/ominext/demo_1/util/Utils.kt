package com.ominext.demo_1.util

import android.content.Context
import android.net.ConnectivityManager
import android.text.format.DateFormat
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*




fun closeKeyboard(context: Context, view: View) {
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun setUserName(context: Context, username: String, userid: String) {
    val sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE)
    sharedPreferences.edit()
            .putString(Constant.FIREBASE_NAME, username)
            .putString(Constant.FIREBASE_ID,userid )
            .apply()
}

fun getUsername(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE)
    return sharedPreferences.getString(Constant.FIREBASE_NAME, "")
}

fun getUserid(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE)
    return sharedPreferences.getString(Constant.FIREBASE_ID, "")
}

fun parseToDate(string: String): String {
    val time = string.toLong()
    return DateFormat.format("dd/MM/yyyy HH:mm:ss", Date(time)).toString()
}

fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return cm.activeNetworkInfo != null
}
