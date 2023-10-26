package com.example.routes

import com.example.dao.daoTime
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.timeRouting(){
    route("/time"){
        get {
            call.respond(daoTime.allTime())
        }
    }
}