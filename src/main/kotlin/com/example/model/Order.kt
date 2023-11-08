package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Order(
    val id: Int,
    val status: Int,
    val numberTickets: Int,
    val userId: Int,
    val tripId: Int,
    val departureStopId: Int,
    val arrivalStopId: Int
)

object Orders : Table("orders") {
    val id = integer("id").autoIncrement()
    val status = integer("status")
    val numberTickets = integer("number_of_tickets")
    val userId = integer("user_id") references (Users.id)
    val tripId = integer("trip_id") references (Trips.id)
    val departureStopId = integer("departure_stop_id") references (StoppingPoints.id)
    val arrivalStopId = integer("arrival_stop_id") references (StoppingPoints.id)

    override val primaryKey = PrimaryKey(id)
}