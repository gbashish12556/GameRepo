package com.example.android.architecture.blueprints.machinecodingporoject.usecase

import com.example.android.architecture.blueprints.machinecodingporoject.data.DataSource
import com.example.android.architecture.blueprints.machinecodingporoject.model.Game
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class GameSelectInCircle(var dataSource: DataSource) :GameSelect {

    private lateinit var games : List<Game>
    private var currentIndex = -1

    override suspend fun getNextGame(): Game {

        runBlocking{
            if (::games.isInitialized){
                fetchData()
            }
        }

        var currentIndex = currentIndex+1
        return games.get((currentIndex)/games.size);

    }

    suspend fun fetchData(){
        games = dataSource.getAllGames()?.list!!
    }
}