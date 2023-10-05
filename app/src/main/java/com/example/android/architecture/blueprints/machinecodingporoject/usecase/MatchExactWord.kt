package com.example.android.architecture.blueprints.machinecodingporoject.usecase

class MatchExactWord :CheckGameResult {
    override suspend fun checkIfComplete(formedWord: String, currentGamename: String): Boolean {
        return if(formedWord.equals(currentGamename)) true else false;
    }
}