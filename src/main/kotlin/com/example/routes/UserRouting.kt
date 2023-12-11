package com.example.routes

import com.example.dao.daoUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.userRouting() {
    route("/user") {
        post("{id}/{firstName}/{lastName}/{phoneNumber}/update") {
            try {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val firstName = call.parameters.getOrFail("firstName")
                val lastName = call.parameters.getOrFail("lastName")

                val phoneNumber = call.parameters.getOrFail("phoneNumber").let { number ->
                    if (number.startsWith("+")) number else "+$number"
                }

                val isSuccess = daoUser.updateUser(id, firstName, lastName, phoneNumber)

                if (isSuccess) {
                    call.respond(HttpStatusCode.OK, "User was edited")
                } else {
                    call.respond(HttpStatusCode.Conflict, "User not found or update failed")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Internal server error: ${e.message}")
            }
        }

        post("{firstName}/{lastName}/{phoneNumber}/{password}/addNewUser") {
            try {
                val firstName = call.parameters.getOrFail("firstName")
                val lastName = call.parameters.getOrFail("lastName")
                val password = call.parameters.getOrFail("password")

                val phoneNumber = call.parameters.getOrFail("phoneNumber").let { number ->
                    if (number.startsWith("+")) number else "+$number"
                }

                val user = daoUser.addNewUser(firstName, lastName, phoneNumber, false, password)

                if (user != null) {
                    call.respond(user)
                } else {
                    call.respond(HttpStatusCode.Conflict, "Create failed")
                }

            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Internal server error: ${e.message}")
            }

        }
        get("{phoneNumber}/{password}/logIn") {
            try {
                val phoneNumber = call.parameters.getOrFail("phoneNumber").let { number ->
                    if (number.startsWith("+")) number else "+$number"
                }
                val password = call.parameters.getOrFail("password")
                val user = daoUser.logInUser(phoneNumber, password)
                if (user != null)
                   call.respond(user)

                else
                    call.respond(HttpStatusCode.NotFound, "User not found")

            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Internal server error: ${e.message}")
            }
        }

        post ( "{id}/{password}/changePassword"){
            try {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val password = call.parameters.getOrFail("password")

                if (daoUser.changePassword(id,password)) {
                    call.respond(HttpStatusCode.OK, "User not found")
                } else {
                    call.respond(HttpStatusCode.Conflict, "User not found")
                }

            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Internal server error: ${e.message}")
            }
        }
    }
}