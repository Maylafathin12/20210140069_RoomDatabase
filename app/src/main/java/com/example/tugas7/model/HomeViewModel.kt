package com.example.tugas7.model

import androidx.lifecycle.ViewModel
import com.example.tugas7.repositori.RepositoriSiswa

class HomeViewModel(private val repositoriSiswa: RepositoriSiswa):ViewModel(){
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L}
    }