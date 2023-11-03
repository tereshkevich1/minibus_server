package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Bus (
    val id: Int,
    val carColor: String,
    val numberSeats: Int,
    val carName: String,
    val carNumber: String
)

object Buses: Table("minibuses"){
    val id = integer("id").autoIncrement()
    val carName = varchar("car_name",50)
    val carNumber = varchar("number",15)
    val carColor = varchar("color",20)
    val numberSeats = integer("number_of_seats")

    override val primaryKey = PrimaryKey(id)
}
