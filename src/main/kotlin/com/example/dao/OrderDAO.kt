package com.example.dao

import com.example.model.Order

interface OrderDAO {
    suspend fun allOrders(): List<Order>
    suspend fun order(id: Int): Order?
    suspend fun addNewOrder(
        userId: Int,
        tripId: Int,
        numberTickets: Int,
        status: Int,
        departureStopId: Int,
        arrivalStopId: Int
    ): Order?

    suspend fun editOrder(id: Int, tripId: Int, status: Int, tickets: Int, stoppingPointsId: Int): Boolean
    suspend fun deleteOrder(id: Int): Boolean
}