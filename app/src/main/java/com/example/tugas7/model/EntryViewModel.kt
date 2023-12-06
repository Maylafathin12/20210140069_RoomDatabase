package com.example.tugas7.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugas7.data.Siswa
import com.example.tugas7.repositori.RepositoriSiswa

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {

    // Berisi status Siswa saat ini
    var uistateSiswa by mutableStateOf(UIStateSiswa())
        private set

    // Fungsi untuk memvalidasi input
    private fun validasiInput(uiState: DetailSiswa = uistateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    // Fungsi untuk update UIState
    private fun updateUiState(detailSiswa: DetailSiswa) {
        uistateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    // Fungsi untuk menyimpan data yang di-entry
    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(uistateSiswa.detailSiswa.toSiswa())
        }
    }
}
    // Mewakili Status Ui untuk Siswa
    data class UIStateSiswa(
        val detailSiswa: DetailSiswa = DetailSiswa(),
        val isEntryValid: Boolean = false
    )

    data class DetailSiswa(
        val id: Int = 0,
        val nama: String = "",
        val alamat: String = "",
        val telpon: String = "",
    )

    // Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya
    fun DetailSiswa.toSiswa(): Siswa = Siswa(
        id = id,
        nama = nama,
        alamat = alamat,
        telpon = telpon
    )

    fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
        detailSiswa = this.toDetailSiswa(),
        isEntryValid = isEntryValid
    )

    fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
        id = id,
        nama = nama,
        alamat = alamat,
        telpon = telpon
    )

