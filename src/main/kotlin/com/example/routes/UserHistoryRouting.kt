package com.example.routes

import com.example.dao.daoOrder
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.userHistoryRouting() {
    route("/userHistory") {

        /*
       get("/{user_id}") {

            val userId = call.parameters.getOrFail<Int>("user_id").toInt()


            val orders = daoOrder.ordersByUserId(userId)

            val userTravelHistoryList: MutableList<UserTravelHistory> = mutableListOf()

            for (order in orders) {
                val trip = daoTrip.tripById(order.tripId) ?: return@get call.respond(HttpStatusCode.NotFound)
                val time = daoTime.time(trip.timeId) ?: return@get call.respond(HttpStatusCode.NotFound)
                val bus = daoBus.bus(trip.minibusId) ?: return@get call.respond(HttpStatusCode.NotFound)
                userTravelHistoryList.add(UserTravelHistory(order, trip, bus, time))
            }


            if (userTravelHistoryList.isEmpty()) {
                call.respond(
                    HttpStatusCode.NotFound,
                    "Orders not found"
                )
            } else {
                call.respond(userTravelHistoryList)
            }
        }*/

        get("/{user_id}") {
            val userId = call.parameters.getOrFail<Int>("user_id").toInt()
            val userTravelHistoryList = daoOrder.getUserTravelHistory(userId)

            // Respond with the list or an error if it's empty
            if (userTravelHistoryList.isEmpty()) {
                call.respond(HttpStatusCode.NotFound, "No travel history found for user.")
            } else {
                call.respond(userTravelHistoryList)
            }
        }
    }

    route("/transport") {
        get("/{minibus_id}") {
            val minibusId = call.parameters.getOrFail<Int>("minibus_id").toInt()

            val transport = daoOrder.getTransport(minibusId)

            // Respond with the list or an error if it's empty
            if (transport == null) {
                call.respond(HttpStatusCode.NotFound, "No travel history found for user.")
            } else {
                call.respond(transport)
            }
        }
    }
}