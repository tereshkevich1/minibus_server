package com.example.model


import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class RoutD(
    val id: Int,
    val startingLocationId: Int,
    val finalLocationId: Int,
    val duration: Int
)

object Routes : Table("routes") {
    val id = integer("id").autoIncrement()
    val startingLocationId = integer("starting_location_id") references (Cities.id)
    val finalLocationId = integer("final_location_id")  references (Cities.id)
    val duration = integer("duration")

    override val primaryKey = PrimaryKey(id)
}