package com.sigit.routing

import com.sigit.db.DatabaseConnection
import com.sigit.entities.NoteEntity
import com.sigit.models.Note
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.from
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
    }
}