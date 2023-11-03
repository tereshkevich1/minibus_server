package com.example.dao

import com.example.model.Bus

interface BusDAO {
    suspend fun allBuses(): List<Bus>
    suspend fun bus(id: Int): Bus?
    suspend fun addNewBus(
        carName: String,
        carColor: String,
        numberSeats: Int,
        carNumber: String
    ): Bus?

    suspend fun editBus(
        id: Int,
        carName: String,
        carColor: String,
        numberSeats: Int,
        carNumber: String
    ): Boolean

    suspend fun deleteBus(id: Int): Boolean
}
