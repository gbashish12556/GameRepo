package com.example.android.architecture.blueprints.machinecodingporoject.usecase

interface CheckGameResult {
    suspend fun checkIfComplete(formedWord:String, currentGamename:String):Boolean
}