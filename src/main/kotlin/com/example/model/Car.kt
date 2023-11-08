package com.example.model

import com.example.model.Buses.autoIncrement
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Car (
    val id: Int,
    val brandModel: String,
    val yearOfManufacture: Int,
    val numberSeats: Int
)

object Cars: Table("cars"){
    val id = integer("id").autoIncrement()
    val brandModel = varchar("brand_model",50)
    val yearOfManufacture = integer("year_of_manufacture")
    val numberSeats = integer("number_of_seats")

    override val primaryKey = PrimaryKey(id)
}