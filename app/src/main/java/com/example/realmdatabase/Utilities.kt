package com.example.realmdatabase

import android.app.Activity
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun getCurrentDate(desireFormate: String = "MM/dd/yyyy HH:mm:ss.SSS"): String {
    return SimpleDateFormat(desireFormate, Locale.getDefault()).format(Date())
}