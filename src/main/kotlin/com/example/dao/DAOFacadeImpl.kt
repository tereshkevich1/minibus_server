package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Article
import com.example.model.Articles
import com.example.model.RoutD
import com.example.model.Routes
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
    startingLocationId = row[Routes.startingLocationId],
    finalLocationId = row[Routes.finalLocationId],
    duration = row[Routes.duration]
)

suspend fun addNewRoute(startingLocationId: Int, finalLocationId: Int, duration: Int): RoutD? =
    dbQuery {
        val insertStatement = Routes.insert {
            it[Routes.startingLocationId] = startingLocationId
            it[Routes.finalLocationId] = finalLocationId
            it[Routes.duration] = duration
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRoute)
    }

// Routes.select{(Routes.startingLocation eq start) and (Routes.finalLocation eq final)}.map(::resultRowToRoute)
suspend fun selRoutes(start: Int, final: Int): List<RoutD> = dbQuery {

    //Routes.selectAll().map(::resultRowToRoute)
    Routes.select { (Routes.startingLocationId eq start) and (Routes.finalLocationId eq final) }.map(::resultRowToRoute)
}


val dao: DAOFacade = DAOFacadeImpl()