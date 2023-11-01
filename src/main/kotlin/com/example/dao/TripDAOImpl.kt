package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.*
import org.jetbrains.exposed.sql.*
import java.time.LocalDate
import java.util.*


class DAOTripImpl : TripDAO {
    private fun resultRowToTrip(row: ResultRow) = Trip(

        id = row[Trips.id],
        minibusId = row[Trips.minibusId],
        driverId = row[Trips.driverId],
        routeId = row[Trips.routeId],
        timeId = row[Trips.timeId],
        price = row[Trips.price],
        numberAvailableSeats = row[Trips.numberAvailableSeats],
        departureDate = row[Trips.departureDate],
    )

    private fun resultRowToRote(row: ResultRow) = RoutD(
        id = row[Routes.id],
        startingLocationId = row[Routes.startingLocationId],
        finalLocationId = row[Routes.finalLocationId],
        duration = row[Routes.duration]
    )

    private fun resultRowToTripTime(row: ResultRow) = TripTime(

        id = row[Trips.id],
        minibusId = row[Trips.minibusId],
        driverId = row[Trips.driverId],
        routeId = row[Trips.routeId],
        timeId = row[Trips.timeId],
        price = row[Trips.price],
        numberAvailableSeats = row[Trips.numberAvailableSeats],
        departureDate = row[Trips.departureDate],
        row[TimeTable.departureTime],
        row[TimeTable.arrivalTime]
    )

    override suspend fun allTrips(): List<Trip> = dbQuery {
        //Cities.select { Cities.id eq id }.singleOrNull()?.let(::resultRowToCity)
        Trips.selectAll().map(::resultRowToTrip)
    }

    override suspend fun trip(
        departureDate: LocalDate,
        startingLocationId: Int,
        finalLocationId: Int
    ): List<TripTime>? {
        val route =
            dbQuery {
                Routes.select {
                    (Routes.startingLocationId eq startingLocationId) and
                            (Routes.finalLocationId eq finalLocationId)
                }
                    .singleOrNull()?.let(::resultRowToRote)
            }
        return if (route != null) {
            dbQuery {
                (Trips innerJoin TimeTable).slice(Trips.columns + TimeTable.departureTime + TimeTable.arrivalTime)
                    .select {
                        (Trips.timeId eq TimeTable.id) and (Trips.routeId eq route.id) and
                                (Trips.departureDate eq departureDate)
                    }
                    .map(::resultRowToTripTime)
            }
        } else null
    }

    override suspend fun tripById(id: Int): Trip? = dbQuery {
        Trips.select{Trips.id eq id}.singleOrNull()?.let(::resultRowToTrip)
    }

    override suspend fun addNewTrip(
        minibusId: Int, driverId: Int, routeId: Int, timeId: Int, price: Int, numberAvailableSeats: Int,
        departureDate: LocalDate
    ): Trip? = dbQuery {

    val insertStatement = Trips.insert {
        it[Trips.driverId] = driverId
        it[Trips.minibusId] = minibusId
        it[Trips.routeId] = routeId
        it[Trips.timeId] = timeId
        it[Trips.price] = price
        it[Trips.numberAvailableSeats] = numberAvailableSeats
        it[Trips.departureDate] = departureDate
    }

    insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToTrip)
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

val daoTrip: TripDAO = DAOTripImpl()