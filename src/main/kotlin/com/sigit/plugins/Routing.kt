package com.sigit.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/iphones/{page}") {
            val pageNumber = call.parameters["page"]

            call.respondText("Your are on Page Number: $pageNumber")
        }
    }
}
