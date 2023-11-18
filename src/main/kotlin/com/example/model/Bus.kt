package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Bus (
    val id: Int,
    val carNumber: String,
    val carId: Int,
    val yearOfManufacture: Int,
    val carColor: String
)

object Buses: Table("minibuses"){
    val id = integer("id").autoIncrement()
    val carNumber = varchar("number",15)
    val carId = integer("car_id") references (Cars.id)
    val yearOfManufacture = integer("year_of_manufacture")
    val carColor = varchar("color",20)

    override val primaryKey = PrimaryKey(id)
}
