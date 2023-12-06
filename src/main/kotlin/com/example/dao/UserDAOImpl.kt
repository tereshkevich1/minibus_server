package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.model.User
import com.example.model.Users
import org.jetbrains.exposed.sql.*

class UserDAOImpl : UserDAO {

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        firstName = row[Users.firstName],
        lastName = row[Users.lastName],
        phoneNumber = row[Users.phoneNumber],
        role = row[Users.role],
        password = row[Users.password]
    )

    override suspend fun allUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun userById(userId: Int): User? {
        TODO("Not yet implemented")
    }

    override suspend fun addNewUser(firstName: String, lastName: String, phoneNumber: String, role: Boolean, password: String): User? =
        dbQuery {
            val exists = Users.select { Users.phoneNumber eq phoneNumber }.any()
            if (!exists) {
                val insertStatement = Users.insert {
                    it[Users.firstName] = firstName
                    it[Users.lastName] = lastName
                    it[Users.phoneNumber] = phoneNumber
                    it[Users.role] = role
                    it[Users.password] = password
                }
                insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
            } else null
        }

    override suspend fun updateUser(id: Int, firstName: String, lastName: String, phoneNumber: String): Boolean =
        dbQuery {
            val exists = Users.select { (Users.id neq id) and (Users.phoneNumber eq phoneNumber) }.any()
            if (!exists) {
                return@dbQuery Users.update({ Users.id eq id }) {
                    it[Users.firstName] = firstName
                    it[Users.lastName] = lastName
                    it[Users.phoneNumber] = phoneNumber
                } > 0
            }
            return@dbQuery false
        }

    override suspend fun deleteUser(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

val daoUser: UserDAO = UserDAOImpl()