package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Driver(
    val id: Int,
    val driverLicenseNumber: Int
)

object Drivers: Table("drivers"){
    val id = integer("id")  references (Users.id)
    val driverLicenseNumber = integer("driver_license_number")
    override val primaryKey = PrimaryKey(id)
}