package com.example.android.architecture.blueprints.machinecodingporoject.data

import com.example.android.architecture.blueprints.machinecodingporoject.model.Response

interface DataSource {
    suspend fun getAllGames():Response?
}