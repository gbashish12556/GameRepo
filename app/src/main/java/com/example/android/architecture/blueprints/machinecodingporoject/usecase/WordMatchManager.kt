package com.example.android.architecture.blueprints.machinecodingporoject.usecase

interface WordMatchManager {
    suspend fun checkIfComplete(formedWord:String, currentGamename:String):Boolean
    suspend fun checkIfCorrectCharacterChosen(character:String, currentGamename:String):Boolean
}