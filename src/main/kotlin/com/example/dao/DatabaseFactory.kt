package com.example.dao

import com.example.model.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://localhost:5432/minibus"
        val password = "admin"
        val userName = "postgres"
        val database = Database.connect(jdbcURL, driverClassName, password = password, user = userName)
        transaction(database) {
            SchemaUtils.create(Articles)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}