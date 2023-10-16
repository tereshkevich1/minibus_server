package com.example.model


import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class RoutD(
    val id: Int,
    val startingLocation: String,
    val finalLocation: String,
    val distance: Int,
    val duration: Int
)

object Routes : Table("routes") {
    val id = integer("id").autoIncrement()
    val startingLocation = varchar("starting_location", 30)
    val finalLocation = varchar("final_location", 30)
    val distance = integer("distance")
    val duration = integer("duration")

    override val primaryKey = PrimaryKey(id)
}