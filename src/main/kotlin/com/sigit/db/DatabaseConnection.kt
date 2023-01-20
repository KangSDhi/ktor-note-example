package com.sigit.db

import org.ktorm.database.Database

object DatabaseConnection {
    val database = Database.connect(
        url = "jdbc:mysql://localhost:3307/ktor_note",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "toor"
    )
}