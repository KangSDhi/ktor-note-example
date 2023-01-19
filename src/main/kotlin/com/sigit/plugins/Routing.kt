package com.sigit.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.reflect.*
import kotlinx.serialization.*
import java.io.File

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

        get("/error"){
            call.respondText("Something went wrong", status = HttpStatusCode.GatewayTimeout)
        }

        get("/user"){
            val responseObject = UserResponse("sigit", "kangsigit@gmail.com")
            call.respond(responseObject)
        }

        get("/headers"){
            call.response.headers.append("Server-Name", "KtorServer")
            call.respondText("Headers Attached")
        }

        get("/fileDownload"){
            val file = File("files/img.png")

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName, "downloadableImage.png"
                ).toString()
            )

            call.respondFile(file)
        }

        get("/fileOpen"){
            val file = File("files/img.png")

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Inline.withParameter(
                    ContentDisposition.Parameters.FileName, "openImage.png"
                ).toString()
            )

            call.respondFile(file)
        }
    }
}

@Serializable
data class UserInfo(val email: String, val password: String)
@Serializable
data class UserResponse(val name: String, val email: String)