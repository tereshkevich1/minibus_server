package com.example.dao

import com.example.model.Trip
import com.example.model.TripTime
import java.time.LocalDate
import java.util.Date

interface TripDAO {
    suspend fun allTrips(): List<Trip>
    suspend fun trip(date: LocalDate, startingLocationId: Int, finalLocationId: Int): List<TripTime>?
    suspend fun addNewTrip(name: String): Trip?
    suspend fun editTrip(id: Int, name: String): Boolean
    suspend fun deleteTrip(id: Int): Boolean
    suspend fun searchTrip(departureDate: Date, routeId: Int): List<Trip>
}