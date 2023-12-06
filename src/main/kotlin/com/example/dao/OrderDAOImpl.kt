package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Order
import com.example.model.Orders
import com.example.model.Trips
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.minus
import org.jetbrains.exposed.sql.SqlExpressionBuilder.plus

class OrderDAOImpl : OrderDAO {

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

    override suspend fun order(id: Int): Order? {
        TODO("Not yet implemented")
    }

    override suspend fun addNewOrder(
        userId: Int,
        tripId: Int,
        numberTickets: Int,
        status: Int,
        departureStopId: Int,
        arrivalStopId: Int
    ): Order? = dbQuery {

        if (Orders.select { (Orders.userId eq userId) and (Orders.tripId eq tripId) }.empty()) {
            val updatedRows = Trips.update({ Trips.id eq tripId }) {
                it[numberAvailableSeats] = numberAvailableSeats.minus(numberTickets)
            }
            if (updatedRows > 0) {

                val insertStatement = Orders.insert {
                    it[Orders.status] = status
                    it[Orders.numberTickets] = numberTickets
                    it[Orders.userId] = userId
                    it[Orders.tripId] = tripId
                    it[Orders.departureStopId] = departureStopId
                    it[Orders.arrivalStopId] = arrivalStopId

                }
                insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToOrder)
            } else return@dbQuery null
        } else return@dbQuery null
    }

    override suspend fun editOrder(id: Int, tripId: Int, status: Int, tickets: Int, stoppingPointsId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOrder(id: Int): Boolean = dbQuery {
        val order = Orders.select { Orders.id eq id }.singleOrNull()?.let(::resultRowToOrder)
        if (order != null) {
            Trips.update({ Trips.id eq order.tripId }) {
                it[Trips.numberAvailableSeats] = Trips.numberAvailableSeats.plus(order.numberTickets)
            }
            Orders.deleteWhere { Orders.id eq id }
        } else {
            return@dbQuery false
        }
        return@dbQuery true
    }
}

val daoOrder: OrderDAO = OrderDAOImpl()