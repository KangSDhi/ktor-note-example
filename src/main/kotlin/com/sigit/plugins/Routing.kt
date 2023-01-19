package com.sigit.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.serialization.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/iphones/{page}") {
            val pageNumber = call.parameters["page"]

            call.respondText("Your are on Page Number: $pageNumber")
        }

        post("/login") {
            val userInfo = call.receive<UserInfo>()
            print(userInfo)
            call.respondText("Everything working.")
        }
    }
}

@Serializable
data class UserInfo(val email: String, val password: String)