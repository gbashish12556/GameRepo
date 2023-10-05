package com.example.android.architecture.blueprints.machinecodingporoject.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.architecture.blueprints.machinecodingporoject.model.Game
import com.example.android.architecture.blueprints.machinecodingporoject.usecase.WordMatchManager
import com.example.android.architecture.blueprints.machinecodingporoject.usecase.GameSelect
import com.example.android.architecture.blueprints.machinecodingporoject.usecase.RandomCharacters
import com.example.android.architecture.blueprints.machinecodingporoject.utils.replaceCharWithHyphen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(val gameSelect:GameSelect,
                                        val wordMatchManager:WordMatchManager,
                                        val randomCharacter:RandomCharacters): ViewModel() {

    private var _currentGame:MutableLiveData<Game> = MutableLiveData()
    var currentGame:LiveData<Game> = _currentGame

    private var _randomisedCharacter:MutableLiveData<List<String>> = MutableLiveData()
    var randomisedCharacter:LiveData<List<String>> = _randomisedCharacter

    private var _wordFormed:MutableList<String> = mutableListOf()

    private var pendingCharacters = "";

    init {
        setCurrentGame()
    }

    suspend fun isCorrectCharcterChosen(char:String): Boolean? {
        return wordMatchManager.checkIfCorrectCharacterChosen(char, pendingCharacters)
    }
    fun setCurrentGame(){

        viewModelScope.launch{

            val game:Game = gameSelect.getNextGame()

            _currentGame.value = game
            pendingCharacters = game.name
            _wordFormed = MutableList(game.name.length) { "" }
            _randomisedCharacter.value = randomCharacter.getRandomCharacters(game.name);

        }

    }

    fun addFormedWord(char: String){
        var index = pendingCharacters.indexOf(char)

        _wordFormed[index] = char

        pendingCharacters = pendingCharacters.replaceCharWithHyphen(index)

        checkIfComplete(_wordFormed)
    }

    fun checkIfComplete(formedWord:List<String>){

        viewModelScope.launch{

            val isCompelete = currentGame.value?.name?.let {
                wordMatchManager.checkIfComplete(formedWord.joinToString(""),
                    it
                )
            };

            if(isCompelete == true){
                setCurrentGame();
            }

        }

    }
}