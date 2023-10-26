package com.example.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.time
import java.time.LocalTime

@Serializable
data class Time(
    val id : Int,
    @Contextual
    val departureTime: LocalTime,
    @Contextual
    val arrivalTime: LocalTime
)

object TimeTable: Table("time_table"){
    val id = integer("id").autoIncrement()
    val departureTime = time("departure_time")
    val arrivalTime = time("arrival_time")
    override val primaryKey = PrimaryKey(id)
}
