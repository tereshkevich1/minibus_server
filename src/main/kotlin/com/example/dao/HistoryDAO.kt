package com.example.dao

import com.example.model.Order
import com.example.model.Transport
import com.example.model.UserTravelHistory


interface HistoryDAO {

    suspend fun allOrders(): List<Order>
    suspend fun ordersByUserId(userId: Int): List<Order>
    suspend fun addNewOrder(
        status: Int,
        numberTickets: Int,
        userId: Int,
        tripId: Int,
        departureStopId: Int,
        arrivalStopId: Int
    ): Order?

    suspend fun editOrder(
        id: Int,
        status: Int,
        numberTickets: Int,
        userId: Int,
        tripId: Int,
        departureStopId: Int,
        arrivalStopId: Int
    ): Boolean

    suspend fun changeStatusOrder(
        id: Int,
        status: Int
    ): Boolean

    suspend fun deleteOrder(id: Int): Boolean

    suspend fun getUserTravelHistory(userId: Int): List<UserTravelHistory>

    suspend fun getTransport(minibusId: Int): Transport?
}

