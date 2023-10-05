package com.example.android.architecture.blueprints.machinecodingporoject.usecase

import android.util.Log

class DefaultRandomCharacter:RandomCharacters {

    private var allChars = mutableListOf<String>("A","B","C","D","E","F","G","H","I","J",
        "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
    )


    private var formedList = mutableListOf<String>();

    override suspend fun getRandomCharacters(gName: String): List<String> {

        formedList.clear()
        val filteredChars = allChars.filter { char -> !gName.contains(char) }
        var gameName = gName

        while (!gameName.equals("")){

            formedList.add(filteredChars.random())
            var selectedChar = gameName.random().toString()
            formedList.add(selectedChar)
            gameName  = gameName.replace(selectedChar,"",true)

        }

        return  formedList

    }
}