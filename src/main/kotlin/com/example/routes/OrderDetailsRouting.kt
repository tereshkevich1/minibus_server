package com.example.routes

import com.example.dao.daoBus
import com.example.dao.daoTime
import com.example.dao.daoTrip
import com.example.model.Details
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.orderDetailsRouting() {
    route("/orderDetails") {
        get("/{id}") {
            val tripId = call.parameters.getOrFail<Int>("id").toInt()
            val trip = daoTrip.tripById(tripId) ?: return@get call.respond(HttpStatusCode.NotFound)

            val bus = daoBus.bus(trip.minibusId) ?: return@get call.respond(HttpStatusCode.NotFound)

            val time = daoTime.time(trip.timeId) ?: return@get call.respond(HttpStatusCode.NotFound)

            val details = Details(trip, bus, time)
            call.respond(details)

        }
    }
}