package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.*
import org.jetbrains.exposed.sql.*

class HistoryDAOImpl : HistoryDAO {

    private fun resultRowToOrder(row: ResultRow) = Order(
        id = row[Orders.id],
        status = row[Orders.status],
        numberTickets = row[Orders.numberTickets],
        userId = row[Orders.userId],
        tripId = row[Orders.tripId],
        departureStopId = row[Orders.departureStopId],
        arrivalStopId = row[Orders.arrivalStopId]

    )

    override suspend fun allOrders(): List<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun ordersByUserId(userId: Int): List<Order> = dbQuery {
        Orders.select { Orders.userId eq userId }.map(::resultRowToOrder)
    }

    override suspend fun addNewOrder(
        status: Int,
        numberTickets: Int,
        userId: Int,
        tripId: Int,
        departureStopId: Int,
        arrivalStopId: Int
    ): Order? = dbQuery {
        val insertStatement = Orders.insert {
            it[Orders.status] = status
            it[Orders.numberTickets] = numberTickets
            it[Orders.userId] = userId
            it[Orders.tripId] = tripId
            it[Orders.departureStopId] = departureStopId
            it[Orders.arrivalStopId] = arrivalStopId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToOrder)
    }

    override suspend fun editOrder(
        id: Int,
        status: Int,
        numberTickets: Int,
        userId: Int,
        tripId: Int,
        departureStopId: Int,
        arrivalStopId: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun changeStatusOrder(id: Int, status: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOrder(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getUserTravelHistory(userId: Int): List<UserTravelHistory> = dbQuery {

        val departureCities = Cities.alias("departureCities")
        val arrivalCities = Cities.alias("arrivalCities")

        val departurePoints = StoppingPoints.alias("departurePoints")
        val arrivalPoints = StoppingPoints.alias("arrivalPoints")

        (Orders innerJoin Trips innerJoin TimeTable innerJoin Routes)
            .innerJoin(departureCities, { Routes.startingLocationId }, { departureCities[Cities.id] })
            .innerJoin(arrivalCities, { Routes.finalLocationId }, { arrivalCities[Cities.id] })
            .innerJoin(departurePoints, { Orders.departureStopId }, { departurePoints[StoppingPoints.id] })
            .innerJoin(arrivalPoints, { Orders.arrivalStopId }, { arrivalPoints[StoppingPoints.id] })
            .select { Orders.userId eq userId }
            .andWhere { Trips.timeId eq TimeTable.id }
            .andWhere { Trips.routeId eq Routes.id }
            .map { resultRow ->
                UserTravelHistory(
                    order = resultRowToOrder(resultRow),
                    trip = resultRowToTrip(resultRow),
                    time = resultRowToTime(resultRow),
                    route = resultRowToRoute(resultRow),
                    departureCity = resultRow[departureCities[Cities.name]],
                    arrivalCity = resultRow[arrivalCities[Cities.name]],
                    departurePoint = resultRow[departurePoints[StoppingPoints.name]],
                    arrivalPoint = resultRow[arrivalPoints[StoppingPoints.name]],
                )
            }
    }
}

val daoOrder: HistoryDAO = HistoryDAOImpl()