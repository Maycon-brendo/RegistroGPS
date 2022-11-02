package com.example.registrogps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _Gravar: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val Gravar: LiveData<Boolean> = _Gravar
    fun setgravar(value: Boolean){
        _Gravar.postValue(value)
    }
}