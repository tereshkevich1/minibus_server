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

    }
}