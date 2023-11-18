package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Car (
    val id: Int,
    val brandModel: String,
    val numberSeats: Int
)

object Cars: Table("cars"){
    val id = integer("id").autoIncrement()
    val brandModel = varchar("brand_model",50)
    val numberSeats = integer("number_of_seats")

    override val primaryKey = PrimaryKey(id)
}