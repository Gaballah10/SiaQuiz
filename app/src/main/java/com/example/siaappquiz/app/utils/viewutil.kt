package com.example.digisapplication.Network.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment


fun View?.add(){
    this?.visibility= View.VISIBLE
}
fun View?.remove(){
    this?.visibility= View.GONE
}

fun Fragment?.toast(message: String?, timeout: Int = Toast.LENGTH_SHORT) {
    try {
        if (this != null)
            Toast.makeText(context, "$message", timeout).show()
    }catch (e:Exception){}
}

fun Activity?.toast(message: String?, timeout: Int = Toast.LENGTH_SHORT) {
    try {
        if (this != null)
            Toast.makeText(this, "$message", timeout).show()
    }catch (e:Exception){}
}