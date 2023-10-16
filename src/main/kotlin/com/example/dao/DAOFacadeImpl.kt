package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Article

import com.example.model.Articles
import com.example.model.RoutD
import com.example.model.Routes

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        body = row[Articles.body],
    )


    override suspend fun allArticles(): List<Article> = dbQuery {
        Articles.selectAll().map(::resultRowToArticle)

    }

    override suspend fun article(id: Int): Article? {
        TODO("Not yet implemented")
    }

    override suspend fun addNewArticle(title: String, body: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun editArticle(id: Int, title: String, body: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

private fun resultRowToRoute(row: ResultRow) = RoutD(
    id = row[Routes.id],
    startingLocation = row[Routes.startingLocation],
    finalLocation = row[Routes.finalLocation],
    distance = row[Routes.distance],
    duration = row[Routes.duration]
)

suspend fun addNewRoute(startingLocation: String, finalLocation: String, distance: Int, duration: Int): RoutD? =
    dbQuery {
        val insertStatement = Routes.insert {
            it[Routes.startingLocation] = startingLocation
            it[Routes.finalLocation] = finalLocation
            it[Routes.distance] = distance
            it[Routes.duration] = duration
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRoute)
    }

// Routes.select{(Routes.startingLocation eq start) and (Routes.finalLocation eq final)}.map(::resultRowToRoute)
suspend fun selRoutes(start: String, final: String): List<RoutD> = dbQuery {

    //Routes.selectAll().map(::resultRowToRoute)
    Routes.select { (Routes.startingLocation eq start) and (Routes.finalLocation eq final) }.map(::resultRowToRoute)
}


val dao: DAOFacade = DAOFacadeImpl()