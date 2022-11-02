package com.example.registrogps.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.registrogps.R

fun Fragment.nav(value: Int){
    findNavController().navigate(value)
}


fun AppCompatActivity.getShatedPrefs(): SharedPreferences {
    return getSharedPreferences(
        getString(R.string.my_preferences_name),
        Context.MODE_PRIVATE
    )
}



fun AppCompatActivity.saveLoginToSharedPrefs(value: String){
    val sharedPrefs = getShatedPrefs()
    val editor = sharedPrefs.edit()
    editor.putString(getString(R.string.login_key),
        value)

    editor.apply()
}

fun AppCompatActivity.getLoginFromSharedPrefs(): String{

    val sharedPrefs = getShatedPrefs()
    return  sharedPrefs.getString(
        getString(R.string.login_key),
        ""
    )?:""
}



