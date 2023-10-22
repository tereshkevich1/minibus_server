package com.example.routes

import com.example.dao.daoCityImpl
import com.example.dao.daoTrip
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.tripsRouting() {
    route("/trip") {
        get {
           // call.respond(daoCityImpl.allCities())
            call.respond(daoTrip.allTrips())
        }
    }
}