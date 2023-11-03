package com.example.dao

import com.example.model.StopPoint

interface StopPointDAO {
    suspend fun allPoints(): List<StopPoint>
    suspend fun pointsByIdCity(cityId: Int): List<StopPoint>?
    suspend fun addNewPoint(name: String): StopPoint?
    suspend fun editPoint(id: Int, name: String): Boolean
    suspend fun deletePoint(id: Int): Boolean
}