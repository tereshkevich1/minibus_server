package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.RoutD
import com.example.model.Routes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select

class RouteDAOImpl : RouteDAO {

    private fun resultRowToRote(row: ResultRow) = RoutD(
        id = row[Routes.id],
        startingLocationId = row[Routes.startingLocationId],
        finalLocationId = row[Routes.finalLocationId],
        duration = row[Routes.duration]
    )

    override suspend fun allRoutes(): List<RoutD> {
        TODO("Not yet implemented")
    }

    override suspend fun route(startingLocationId: Int, finalLocationId: Int): RoutD? = dbQuery {
        Routes.select { (Routes.startingLocationId eq startingLocationId) and (Routes.finalLocationId eq finalLocationId) }
            .singleOrNull()?.let(::resultRowToRote)
    }

    override suspend fun addNewRoute(name: String): RoutD? {
        TODO("Not yet implemented")
    }

    override suspend fun editRoute(id: Int, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRoute(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}