package com.example.routes

import com.example.dao.daoCityImpl
import com.example.model.City
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.citiesRouting() {
    route("/city") {
        get {
            call.respond(daoCityImpl.allCities())
        }
        get("/{id}") {

            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respond(mapOf("article" to daoCityImpl.city(id)))

        }
        delete ("/{id}") {

            val id = call.parameters.getOrFail<Int>("id").toInt()
            daoCityImpl.deleteCity(id)
            call.respondText("City deleted correctly", status = HttpStatusCode.Accepted)

        }
        post {
            val city = call.receive<City>()
            val name = city.name
            daoCityImpl.addNewCity(name)
            call.respondText("City stored correctly", status = HttpStatusCode.Created)
        }

    }
}