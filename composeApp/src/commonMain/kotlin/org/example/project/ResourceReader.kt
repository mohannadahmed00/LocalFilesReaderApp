package org.example.project

expect class ResourceReader {
    suspend fun getResourceAsString(fileName: String): String
}