package com.example.routes

import com.example.dao.daoOrder
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.orderRouting() {
    route("/order") {

        post("{id}/delete") {
            try {
                val id = call.parameters.getOrFail<Int>("id").toInt()

                if (daoOrder.deleteOrder(id)) {
                    call.respond(HttpStatusCode.OK, "Order deleted")
                } else {
                    call.respond(HttpStatusCode.Conflict, "Delete failed")
                }

            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Internal server error: ${e.message}")
            }
        }

        post("/add/{userId}/{tripId}/{numberTickets}/{status}/{departureStopId}/{arrivalStopId}") {
            try {
                val userId = call.parameters.getOrFail<Int>("userId").toInt()
                val tripId = call.parameters.getOrFail<Int>("tripId").toInt()
                val numberTickets = call.parameters.getOrFail<Int>("numberTickets").toInt()
                val status = call.parameters.getOrFail<Int>("status").toInt()
                val departureStopId = call.parameters.getOrFail<Int>("departureStopId").toInt()
                val arrivalStopId = call.parameters.getOrFail<Int>("arrivalStopId").toInt()


                val order = daoOrder.addNewOrder(userId, tripId, numberTickets, status, departureStopId, arrivalStopId)

                if (order != null) {
                    call.respond(HttpStatusCode.Created, "Order created")
                } else {
                    call.respond(HttpStatusCode.Conflict, "Failed to create order")
                }

            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Internal server error: ${e.message}")
            }
        }

    }
}