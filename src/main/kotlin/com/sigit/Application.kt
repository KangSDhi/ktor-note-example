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
import org.ktorm.dsl.insert

fun main(){
    embeddedServer(Netty, port=8080, host="0.0.0.0"){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        configureRouting()

        val database = Database.connect(
            url = "jdbc:mysql://localhost:3307/ktor_note",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "toor"
        )

        database.insert(NoteEntity){
            set(it.note, "Wash Clothes")
        }

        database.insert(NoteEntity){
            set(it.note, "Buy Groceries")
        }

        database.insert(NoteEntity){
            set(it.note, "Workout")
        }
    }.start(wait = true)
}


