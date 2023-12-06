package com.example.tugas7.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao {
    @Insert(onConflict =OnConflictStrategy.IGNORE)
    suspend fun insertSiswa(siswa: Siswa)

    @Update
    suspend fun updateSiswa(siswa: Siswa)

    @Delete
    suspend fun deleteSiswa(siswa: Siswa)

    @Query("SELECT * from tblSiswa WHERE id = :id")
    fun getSiswa (id: Int): Flow<Siswa>

    @Query("SELECT * from tblSiswa ORDER BY nama ASC")
    fun getAllSiswa (): Flow<List<Siswa>>
}

