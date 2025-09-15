package org.example.project

import platform.Foundation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.cinterop.*

@OptIn(ExperimentalForeignApi::class)
actual class ResourceReader {

    actual suspend fun getResourceAsString(fileName: String) =
        withContext(Dispatchers.Default) {
            val path = getResourcePath(fileName)
            try {
                NSString.stringWithContentsOfFile(
                    path,
                    encoding = NSUTF8StringEncoding,
                    error = null
                )
                    ?: run {
                        error("Couldn't load resource: $fileName. Error: ")
                    }
            } catch (e: Exception) {
                "Failed to read resource $fileName: ${e.message}"
            }
        }

    private fun getResourcePath(fileName: String) =
        NSBundle.mainBundle.pathForResource("compose-resources/json/$fileName", ofType = "json")
            ?: "no"
}