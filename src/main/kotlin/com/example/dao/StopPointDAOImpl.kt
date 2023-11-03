package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.StopPoint
import com.example.model.StoppingPoints
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

class StopPointDAOImpl : StopPointDAO {

    private fun resultRowToStopPoint(row: ResultRow) = StopPoint(
        id = row[StoppingPoints.id],
        name = row[StoppingPoints.name],
        cityId = row[StoppingPoints.cityId]
    )

    override suspend fun allPoints(): List<StopPoint> {
        TODO("Not yet implemented")
    }

    override suspend fun pointsByIdCity(cityId: Int): List<StopPoint> = dbQuery {
        StoppingPoints.select { StoppingPoints.cityId eq cityId }.map(::resultRowToStopPoint)
    }

    override suspend fun addNewPoint(name: String): StopPoint? {
        TODO("Not yet implemented")
    }

    override suspend fun editPoint(id: Int, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deletePoint(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

val daoStop: StopPointDAO = StopPointDAOImpl()