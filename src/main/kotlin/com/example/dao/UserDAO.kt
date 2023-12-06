package com.example.dao

import com.example.model.User

interface UserDAO {
    suspend fun allUsers(): List<User>
    suspend fun userById(userId: Int,): User?
    suspend fun addNewUser(firstName: String, lastName: String, phoneNumber: String, role: Boolean, password: String): User?
    suspend fun updateUser(id: Int, firstName: String,lastName: String, phoneNumber: String): Boolean
    suspend fun deleteUser(id: Int): Boolean
}