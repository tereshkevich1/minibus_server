package com.example.dao

import com.example.model.Trip
import java.util.Date

interface DAOTrip {
    suspend fun allTrips(): List<Trip>
    suspend fun trip(id: Int): Trip?
    suspend fun addNewTrip(name: String): Trip?
    suspend fun editTrip(id: Int, name: String): Boolean
    suspend fun deleteTrip(id: Int): Boolean
    suspend fun searchTrip(departureDate: Date, routeId: Int): List<Trip>
}