package com.example.routes

import com.example.dao.daoStop
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.stopPointRouting() {

    route("/stops") {
        get("/{city_id}") {
            val cityId = call.parameters.getOrFail<Int>("city_id").toInt()
            val stoppingPoints = daoStop.pointsByIdCity(cityId) ?: return@get call.respond(
                HttpStatusCode.NotFound,
                "Stop point not found"
            )
            call.respond(stoppingPoints)
        }
    }
}