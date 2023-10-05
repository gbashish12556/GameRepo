package com.example.android.architecture.blueprints.machinecodingporoject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.architecture.blueprints.machinecodingporoject.model.Game
import com.example.android.architecture.blueprints.machinecodingporoject.usecase.CheckGameResult
import com.example.android.architecture.blueprints.machinecodingporoject.usecase.GameSelect
import com.example.android.architecture.blueprints.machinecodingporoject.usecase.RandomCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(val gameSelect:GameSelect,
                    val checkGameResult:CheckGameResult,
                    val randomCharacter:RandomCharacters): ViewModel() {

    private var _currentGame:MutableLiveData<Game> = MutableLiveData()
    var currentGame:LiveData<Game> = _currentGame

    private var _randomisedCharacter:MutableLiveData<List<String>> = MutableLiveData()
    var randomisedCharacter:LiveData<List<String>> = _randomisedCharacter

    private var _wordFormed:MutableList<String> = mutableListOf()

    init {
        setCurrentGame()
    }

    fun isCorrectCharcterChosen(char:String): Boolean? {
        return currentGame.value?.name?.contains(char)
    }
    fun setCurrentGame(){

        viewModelScope.launch{

            val game:Game = gameSelect.getNextGame()

            _currentGame.value = game
            _wordFormed = MutableList(game.name.length) { "" }
            _randomisedCharacter.value = randomCharacter.getRandomCharacters(game.name);

        }

    }

    fun addFormedWord(char: String, index:Int){
        _wordFormed.add(index, char)
        checkIfComplete(_wordFormed)
    }

    fun checkIfComplete(formedWord:List<String>){

        viewModelScope.launch{

            val isCompelete = currentGame.value?.name?.let {
                checkGameResult.checkIfComplete(formedWord.toString(),
                    it
                )
            };

            if(isCompelete == true){
                setCurrentGame();
            }

        }

    }
}