package com.example.model

import com.example.model.Drivers.references
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class StopPoint (
    val id : Int,
    val name: String,
    val cityId: Int
)

object StoppingPoints: Table("stops"){
    val id = integer("id").autoIncrement()
    val name = varchar("name",255)
    val cityId = integer("city_id") references (Cities.id)
    override val primaryKey = PrimaryKey(id)
}