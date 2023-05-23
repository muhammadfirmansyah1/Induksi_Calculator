package com.example.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalcVM : ViewModel() {

    var input : MutableLiveData<String> = MutableLiveData("")
    var output : MutableLiveData<String> = MutableLiveData("0")

    fun setInput(string : String){
        input.value = string
    }

}