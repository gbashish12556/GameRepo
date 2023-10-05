package com.example.android.architecture.blueprints.machinecodingporoject.usecase

interface RandomCharacters {
    suspend fun getRandomCharacters(gameName:String):List<String>
}