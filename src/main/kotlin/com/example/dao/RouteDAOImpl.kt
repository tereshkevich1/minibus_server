package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.RoutD
import com.example.model.Routes
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class RouteDAOImpl : RouteDAO {

    private fun resultRowToRoute(row: ResultRow) = RoutD(
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
            .singleOrNull()?.let(::resultRowToRoute)
    }

    override suspend fun addNewRoute(startingLocationId: Int, finalLocationId: Int): RoutD? = dbQuery {

        val insertStatement = Routes.insert {
            it[Routes.startingLocationId] = startingLocationId
            it[Routes.finalLocationId] = finalLocationId
            it[Routes.duration] = 250
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRoute)
    }


    override suspend fun editRoute(id: Int, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRoute(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

val daoRoute: RouteDAO = RouteDAOImpl()