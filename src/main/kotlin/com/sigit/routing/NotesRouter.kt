package com.sigit.routing

import com.sigit.db.DatabaseConnection
import com.sigit.entities.NoteEntity
import com.sigit.models.Note
import com.sigit.models.NoteRequest
import com.sigit.models.NoteResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.map
import org.ktorm.dsl.select

fun Application.notesRoutes(){
    val db = DatabaseConnection.database

    routing {
        get("/notes") {
            val notes = db.from(NoteEntity).select()
                .map {
                    val id = it[NoteEntity.id]
                    val note = it[NoteEntity.note]
                    Note(id ?: -1, note ?: "")
                }
            call.respond(notes)
        }

        post("/notes") {
            val request = call.receive<NoteRequest>()
            val result = db.insert(NoteEntity) {
                set(it.note, request.note)
            }

            if (result == 1){
                // Send successfully response to the client
                call.respond(HttpStatusCode.OK, NoteResponse(
                    success = true,
                    data = "Values has been sucessfully inserted"
                ))
            } else {
                // Send failure response to the client
                call.respond(HttpStatusCode.BadRequest, NoteResponse(
                    success = false,
                    data = "Failed to insert values."
                ))
            }
        }
    }
}