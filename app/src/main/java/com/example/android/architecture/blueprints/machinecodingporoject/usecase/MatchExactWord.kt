package com.example.android.architecture.blueprints.machinecodingporoject.usecase

class MatchExactWord :WordMatchManager {
    override suspend fun checkIfComplete(formedWord: String, currentGamename: String): Boolean {
        return if(formedWord.equals(currentGamename)) true else false;
    }

    override suspend fun checkIfCorrectCharacterChosen(
        character: String,
        currentGamename: String
    ): Boolean {
        return currentGamename.contains(character)
    }
}