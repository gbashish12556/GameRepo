package com.example.android.architecture.blueprints.machinecodingporoject.data

import android.content.Context
import com.example.android.architecture.blueprints.machinecodingporoject.R
import com.example.android.architecture.blueprints.machinecodingporoject.model.Response
import com.google.gson.Gson

class JsonDataSource(val context:Context):DataSource {

    override suspend fun getAllGames(): Response {
        val text = context.resources.openRawResource(R.raw.Data)
            .bufferedReader().use { it.readText() }

        var response = Gson().fromJson<Response>(text, Response::class.java)
        return response
    }

}