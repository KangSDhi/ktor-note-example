package com.sigit

import com.sigit.entities.NoteEntity
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.sigit.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun main(){
    embeddedServer(Netty, port=8080, host="0.0.0.0"){
        configureRouting()
        install(ContentNegotiation){
            json()
        }
    }.start(wait = true)
}


