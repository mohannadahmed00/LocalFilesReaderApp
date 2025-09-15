package org.example.project

import localfilesreaderapp.composeapp.generated.resources.Res

class JsonResourceParser {
    suspend fun loadUserData(): String? = Res.readBytes("files/data.json").decodeToString()
}
