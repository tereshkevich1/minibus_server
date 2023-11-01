package com.example.routes

import com.example.dao.daoDriver
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.driverRouting() {
    route("/driver") {
        get("/{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val driver = daoDriver.driver(id)
            if (driver != null) {
                call.respond(driver)
            } else {
                call.respond(HttpStatusCode.NotFound, "Driver not found")
            }
        }
    }
}