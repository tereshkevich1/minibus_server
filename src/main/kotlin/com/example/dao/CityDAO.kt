package com.example.dao

import com.example.model.City

interface CityDAO {
    suspend fun allCities(): List<City>
    suspend fun city(id: Int): City?
    suspend fun addNewCity(name: String): City?
    suspend fun editCity(id: Int, name: String): Boolean
    suspend fun deleteCity(id: Int): Boolean
}