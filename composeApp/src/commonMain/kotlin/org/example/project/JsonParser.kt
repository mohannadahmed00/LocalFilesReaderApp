package org.example.project

class JsonResourceParser(private val resourceReader: ResourceReader) {
    suspend fun loadUserData(): String? {
        val dataString = resourceReader.getResourceAsString("data.json")
        return dataString
    }
}