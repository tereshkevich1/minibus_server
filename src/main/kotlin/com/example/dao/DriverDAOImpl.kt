package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Driver
import com.example.model.Drivers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

class DriverDAOImpl : DriverDAO {

    private fun resultRowToDriver(row: ResultRow) = Driver(
        id = row[Drivers.id],
        driverLicenseNumber = row[Drivers.driverLicenseNumber]
    )

    override suspend fun allDrivers(): List<Driver> {
        TODO("Not yet implemented")
    }

    override suspend fun driver(id: Int): Driver? = dbQuery {
        Drivers.select { Drivers.id eq id }.singleOrNull()?.let(::resultRowToDriver)

    }

    override suspend fun addNewDriver(userId: Int): Driver? {
        TODO("Not yet implemented")
    }

    override suspend fun editDriver(id: Int, driverLicense: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDriver(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

val daoDriver: DriverDAO = DriverDAOImpl()