package com.example.dao

import com.example.model.City
import com.example.model.Driver

interface DriverDAO {
    suspend fun allDrivers(): List<Driver>
    suspend fun driver(id: Int): Driver?
    suspend fun addNewDriver(userId: Int): Driver?
    suspend fun editDriver(id: Int, driverLicense: Int): Boolean
    suspend fun deleteDriver(id: Int): Boolean
}