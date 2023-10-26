package com.example.model


import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Minibus(
    val id: Int,
    val number: String,
    val color: String,
    val numberOfSeats: Int,
    val carId: Int
)

object Minibuses : Table("minibuses") {
    val id = integer("id").autoIncrement()
    val number = varchar("number", 15)
    val color = varchar("color", 20)
    val numberOfSeats = integer("number_of_seats")
    val carId = integer("car_id") //  references (CarsName.id)

    override val primaryKey = PrimaryKey(id)
}