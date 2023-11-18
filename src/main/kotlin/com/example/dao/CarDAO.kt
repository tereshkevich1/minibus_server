package com.example.dao

import com.example.model.Car
import com.example.model.City

interface CarDAO {
    suspend fun allCars(): List<Car>
    suspend fun car(id: Int): Car?
    suspend fun addNewCar(brandModel: String, numberSeats: Int): Car?
    suspend fun editCar(id: Int, name: String, numberSeats: Int): Boolean
    suspend fun deleteCar(id: Int): Boolean
}


