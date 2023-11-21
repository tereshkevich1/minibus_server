package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Order
import com.example.model.Orders
import com.example.model.Trips
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.plus
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

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
        status: Int,
        tickets: Int,
        stoppingPointsId: Int
    ): Order? {
        TODO("Not yet implemented")
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