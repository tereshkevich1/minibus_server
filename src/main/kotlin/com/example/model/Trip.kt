package com.example.model


import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate


@Serializable
data class Trip(
    val id: Int,
    val minibusId: Int,
    val driverId: Int,
    val routeId: Int,
    val timeId: Int,
    val price: Int,
    val numberAvailableSeats: Int,
    @Contextual
    val departureDate: LocalDate
)

object Trips : Table("trips") {
    val id = integer("id").autoIncrement()
    val minibusId = integer("minibus_id")
    val driverId = integer("driver_id")
    val routeId = integer("route_id")
    val timeId = integer("time_id")
    val price =  integer("price")
    val numberAvailableSeats = integer("number_available_seats")
    val departureDate = date("departure_date")


    override val primaryKey = PrimaryKey(id)
}