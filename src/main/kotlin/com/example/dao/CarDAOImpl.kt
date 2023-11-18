package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Car
import com.example.model.Cars
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

fun resultRowToCar(row: ResultRow) = Car(
    id = row[Cars.id],
    brandModel = row[Cars.brandModel],
    numberSeats = row[Cars.numberSeats]
)

class CarDAOImpl : CarDAO {

    override suspend fun allCars(): List<Car> {
        TODO("Not yet implemented")
    }

    override suspend fun car(id: Int): Car? = dbQuery {
        Cars.select { Cars.id eq id }.singleOrNull()?.let(::resultRowToCar)
    }

    override suspend fun addNewCar(brandModel: String, numberSeats: Int): Car? {
        TODO("Not yet implemented")
    }

    override suspend fun editCar(id: Int, name: String, numberSeats: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCar(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

val daoCar: CarDAO = CarDAOImpl()