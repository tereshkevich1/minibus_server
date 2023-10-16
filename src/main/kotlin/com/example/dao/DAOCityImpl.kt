package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Cities
import com.example.model.City
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOCityImpl : DAOCity {

    private fun resultRowToCity(row: ResultRow) = City(
        id = row[Cities.id],
        name = row[Cities.name]
    )

    override suspend fun allCities(): List<City> = dbQuery {
        Cities.selectAll().map(::resultRowToCity)
    }

    override suspend fun city(id: Int): City? = dbQuery {
        Cities.select { Cities.id eq id }.singleOrNull()?.let(::resultRowToCity)
    }

    override suspend fun addNewCity(name: String): City? = dbQuery {
        val insertStatement = Cities.insert {
            it[Cities.name] = name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCity)
    }

    override suspend fun editCity(id: Int, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCity(id: Int): Boolean = dbQuery {
        Cities.deleteWhere { Cities.id eq id } > 0
    }

}


val daoCityImpl: DAOCity = DAOCityImpl()