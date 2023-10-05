package com.example.android.architecture.blueprints.machinecodingporoject.usecase

import com.example.android.architecture.blueprints.machinecodingporoject.model.Game

interface GameSelect {
    suspend fun getNextGame(): Game
}