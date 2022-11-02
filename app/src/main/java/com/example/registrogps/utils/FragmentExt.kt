package com.example.registrogps.utils

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.nav(value: Int){
    findNavController().navigate(value)
}



