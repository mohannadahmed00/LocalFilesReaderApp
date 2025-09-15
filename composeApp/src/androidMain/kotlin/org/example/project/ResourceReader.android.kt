package org.example.project

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException

actual class ResourceReader(private val context: Context) {
    actual suspend fun getResourceAsString(fileName: String) =
        withContext(Dispatchers.IO) {
            try {
                context.assets.open(fileName).use { inputStream ->
                    inputStream.readBytes().decodeToString()
                }
            } catch (e: IOException) {
                "Failed to read resource $fileName: ${e.message}"
            }
        }
}