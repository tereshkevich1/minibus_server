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

        post("{firstName}/{lastName}/{phoneNumber}/addNewUser") {
            try {
                val firstName = call.parameters.getOrFail("firstName")
                val lastName = call.parameters.getOrFail("lastName")

                val phoneNumber = call.parameters.getOrFail("phoneNumber").let { number ->
                    if (number.startsWith("+")) number else "+$number"
                }

                val user = daoUser.addNewUser(firstName, lastName, phoneNumber, false)

                if (user != null) {
                    call.respond(HttpStatusCode.OK, "User added, id: ${user.id}")
                } else {
                    call.respond(HttpStatusCode.Conflict, "Create failed")
                }

            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Internal server error: ${e.message}")
            }

        }
    }
}