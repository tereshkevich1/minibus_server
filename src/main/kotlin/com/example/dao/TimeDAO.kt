package com.example.dao

import com.example.model.Time

interface TimeDAO {
    suspend fun allTime(): List<Time>
    suspend fun time(id: Int): Time?
    suspend fun addNewTime(name: String): Time?
    suspend fun editTime(id: Int, name: String): Boolean
    suspend fun deleteTime(id: Int): Boolean
}