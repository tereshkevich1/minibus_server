package com.example.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.sql.Time

@Serializable
data class Time(
    val id : Int,
    @Contextual
    val departureTime: Time,
    @Contextual
    val arrivalTime: Time
)

object TimeTable: Table("time_table"){
    val id = integer("id").autoIncrement()
   // val departureTime =
    //val arrivalTime
    override val primaryKey = PrimaryKey(id)
}
