package com.example.routes

import com.example.dao.daoTrip
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import java.time.LocalDate
import java.time.format.DateTimeParseException

fun Route.tripsRouting() {
    route("/trip") {
        get {
           // call.respond(daoCityImpl.allCities())
            call.respond(daoTrip.allTrips())
        }
        get("/{startingLocationId}/{finalLocationId}/{departureDate}") {
            val start = call.parameters.getOrFail<Int>("startingLocationId").toInt()
            val final = call.parameters.getOrFail<Int>("finalLocationId").toInt()
            val departureDateParam = call.parameters.getOrFail<String>("departureDate")

            // Attempt to parse the departureDate parameter into a LocalDate
            val departureDate = try {
                LocalDate.parse(departureDateParam)
            } catch (e: DateTimeParseException) {
                call.respond(HttpStatusCode.BadRequest, "Invalid departureDate format")
                return@get
            }


            val trips = daoTrip.trip(departureDate, start, final)
            call.respond(trips ?: emptyList())

        }
    }
}