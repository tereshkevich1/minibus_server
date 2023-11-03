package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.Time
import com.example.model.TimeTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOTimeImpl : TimeDAO {
    private fun resultRowToTime(row: ResultRow) = Time(
        id = row[TimeTable.id],
        departureTime = row[TimeTable.departureTime],
        arrivalTime = row[TimeTable.arrivalTime]
    )

    override suspend fun allTime(): List<Time> = dbQuery {
        TimeTable.selectAll().map(::resultRowToTime)
    }

    override suspend fun time(id: Int): Time? = dbQuery {
        TimeTable.select { TimeTable.id eq id }.singleOrNull()?.let(::resultRowToTime)
    }

    override suspend fun addNewTime(name: String): Time? {
        TODO("Not yet implemented")
    }

    override suspend fun editTime(id: Int, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTime(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

val daoTime: TimeDAO = DAOTimeImpl()