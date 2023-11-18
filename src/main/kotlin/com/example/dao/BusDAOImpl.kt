package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Bus
import com.example.model.Buses
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select


fun resultRowToBus(row: ResultRow) = Bus(
    id = row[Buses.id],
    carNumber = row[Buses.carNumber],
    carId = row[Buses.carId],
    yearOfManufacture = row[Buses.yearOfManufacture],
    carColor = row[Buses.carColor]
)

class BusDAOImpl : BusDAO {


    override suspend fun allBuses(): List<Bus> {
        TODO("Not yet implemented")
    }

    override suspend fun bus(id: Int): Bus? = dbQuery {
        Buses.select { Buses.id eq id }.singleOrNull()?.let(::resultRowToBus)
    }

    override suspend fun addNewBus(carName: String, carColor: String, numberSeats: Int, carNumber: String): Bus? {
        TODO("Not yet implemented")
    }

    override suspend fun editBus(
        id: Int,
        carName: String,
        carColor: String,
        numberSeats: Int,
        carNumber: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBus(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

val daoBus: BusDAO = BusDAOImpl()