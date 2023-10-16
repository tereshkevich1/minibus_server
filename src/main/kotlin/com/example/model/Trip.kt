package com.example.model


import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Trip(
    val id: Int,
    val price: Int,
    val numberAvailableSeats: Int,
    val minibusId: Int,
    val routeId: Int
)

object Trips : Table("trips") {
    val id = integer("id").autoIncrement()
    val price =  integer("price")
    val numberAvailableSeats = integer("number_available_seats")
    val minibusId = integer("minibus_id")
    val routeId = integer("route_id")

    override val primaryKey = PrimaryKey(id)
}