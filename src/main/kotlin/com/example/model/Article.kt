package com.example.model


import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Article(val id: Int, val title: String, val body: String)

@Serializable
data class Art(val title: String, val body: String)

object Articles : Table("article_table") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 1024)

    override val primaryKey = PrimaryKey(id)
}