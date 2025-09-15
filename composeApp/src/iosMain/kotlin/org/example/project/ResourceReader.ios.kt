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
                ) ?: ""
            } catch (e: Exception) {
                "Failed to read resource $fileName: ${e.message}"
            }
        }

    private fun getResourcePath(fileName: String): String {
        val bundle = NSBundle.mainBundle

        return if (fileName.contains('.')) {
            val dotIndex = fileName.lastIndexOf('.')
            val name = fileName.substring(0, dotIndex)
            val extension = fileName.substring(dotIndex + 1)
            bundle.pathForResource(name, extension) ?: "no data"
        } else {
            bundle.pathForResource(fileName, null) ?: "no data"
        }
    }
}