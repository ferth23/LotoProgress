package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoteriaViewModel: ViewModel() {
    private val _lotoNumbers = mutableStateOf(emptyList<Int>())
    val lotoNumbers: State<List<Int>> = _lotoNumbers

    var isLoading by mutableStateOf ( false )
        private set

    fun generateLotoNumbers() {
        _lotoNumbers.value = emptyList()

        viewModelScope.launch {
            val numbers = ( 1 .. 60 ).shuffled().take(6).sorted()

            for ( number in numbers ) {
                isLoading = true
                delay ( 1000 )

                isLoading = false
                _lotoNumbers.value += number
                delay ( 1000 )
            }
        }
    }
}