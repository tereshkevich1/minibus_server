package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class City(
    val id: Int,
    val name: String
)

object Cities: Table("cities"){
    val id = integer("id").autoIncrement()
    val name = varchar("name",20)

    override val primaryKey = PrimaryKey(id)
}