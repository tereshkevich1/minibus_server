package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val role: Boolean,
    val password: String
)

object Users : Table("users") {

    val id = integer("id").autoIncrement()
    val firstName = varchar("first_name", 30)
    val lastName = varchar("last_name", 30)
    val phoneNumber = varchar("phone_number", 30)
    val role = bool("role")
    val password = varchar("password", 30)

    override val primaryKey = PrimaryKey(id)
}