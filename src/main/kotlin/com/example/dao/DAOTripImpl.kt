package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Trip
import com.example.model.Trips
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import java.util.*


class DAOTripImpl : DAOTrip {
    private fun resultRowToTrip(row: ResultRow) = Trip(

        id = row[Trips.id],
        minibusId = row[Trips.minibusId],
        driverId = row[Trips.driverId],
        routeId = row[Trips.routeId],
        timeId = row[Trips.timeId],
        price = row[Trips.price],
        numberAvailableSeats = row[Trips.numberAvailableSeats],
        departureDate = row[Trips.departureDate]
    )

    override suspend fun allTrips(): List<Trip>  = dbQuery {
        //Cities.select { Cities.id eq id }.singleOrNull()?.let(::resultRowToCity)
        Trips.selectAll().map(::resultRowToTrip)
    }

    override suspend fun trip(id: Int): Trip? {
        TODO("Not yet implemented")
    }

    override suspend fun addNewTrip(name: String): Trip? {
        TODO("Not yet implemented")
    }

    override suspend fun editTrip(id: Int, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTrip(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun searchTrip(departureDate: Date, routeId: Int): List<Trip> {

        TODO("Not yet implemented")
    }
}

val daoTrip : DAOTrip = DAOTripImpl()