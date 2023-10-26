package com.example.plugins

import com.example.dao.addNewRoute
import com.example.dao.dao
import com.example.dao.selRoutes
import com.example.model.Article
import com.example.model.RoutD
import com.example.routes.citiesRouting
import com.example.routes.timeRouting
import com.example.routes.tripsRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
    routing {
        citiesRouting()
        tripsRouting()
        timeRouting()
        route("/art"){

            get {
                call.respond(mapOf("articles" to dao.allArticles()))
            }

            post {
                val art = call.receive<Article>()
                val title = art.title
                val body = art.body

                val article = dao.addNewArticle(title, body)
                call.respondText("Customer stored correctly $body, $title", status = HttpStatusCode.Created)
            }
        }
        route("/route"){
            post{
                val route = call.receive<RoutD>()
                val start = route.startingLocationId
                val final = route.finalLocationId
                val duration = route.duration
                addNewRoute(start,final,duration)
                call.respondText("Route stored correctly", status = HttpStatusCode.Created)
            }
            get("/user/{start}/{final}") {
                val start = call.parameters.getOrFail<Int>("start").toInt()
                val final = call.parameters.getOrFail<Int>("final").toInt()
                call.respond(mapOf("routes" to  selRoutes(start,final)))
            }
        }

    }
}
