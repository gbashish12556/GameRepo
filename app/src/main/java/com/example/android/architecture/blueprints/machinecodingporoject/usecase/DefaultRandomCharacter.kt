package com.example.android.architecture.blueprints.machinecodingporoject.usecase

class DefaultRandomCharacter:RandomCharacters {

    private var allChars = mutableListOf<String>("a","b","c","d","e","f","g","h","i","j",
        "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
    )


    private var formedList = mutableListOf<String>();

    override suspend fun getRandomCharacters(gameName: String): List<String> {

        while (!gameName.isEmpty()){

            for(i in 0..(0..2).random()){
                formedList.add(allChars.random())
            }

            var selectedChar = gameName.random().toString()
            formedList.add(selectedChar)
            gameName.replace(selectedChar,"",false)

        }

        return  formedList

    }
}