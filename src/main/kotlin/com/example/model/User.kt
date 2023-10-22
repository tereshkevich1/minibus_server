package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val number: String,
    val role: Boolean
)

object Users: Table("users"){

    val id = integer("id").autoIncrement()
    val firstName = varchar("first_name", 30)
    val lastName = varchar("last_name", 30)
    val number = varchar("number", 30)
    val role = bool("role")

    override val primaryKey = PrimaryKey(id)
}