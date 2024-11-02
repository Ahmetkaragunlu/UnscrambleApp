package com.ahmetkaragunlu.unscrambleapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ahmetkaragunlu.unscrambleapp.data.allWords
import com.ahmetkaragunlu.unscrambleapp.data.correctGuessPoints
import com.ahmetkaragunlu.unscrambleapp.data.maxWordCount
import com.ahmetkaragunlu.unscrambleapp.model.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var usedWords = mutableSetOf<String>()

    init {
        selectRandomWord()
    }

    var userGuess by mutableStateOf("")
        private set
    var showDialog by mutableStateOf(false)
        private set

    fun updateUserGuess(newUserGuess: String) {
        userGuess = newUserGuess
    }

    fun selectRandomWord() {
        var word: String
        do {
            word = allWords.random()
        } while (usedWords.contains(word))

        usedWords.add(word)
        shuffleWord(word = word)
    }

    fun shuffleWord(word: String) {
        var shuffledWord: String
        do {
            shuffledWord = word.toList().shuffled().joinToString("")
        } while (shuffledWord == word)

        _uiState.update { currentSate ->
            currentSate.copy(
                currentScrambleWord = shuffledWord,
                currentWordCount = _uiState.value.currentWordCount.inc(),
                originalWord = word,
                isError = false
            )
        }
    }

    fun checkUserGuess() {
        _uiState.update { currentState ->
            if (_uiState.value.originalWord.equals(userGuess, ignoreCase = true)) {
                currentState.copy(
                    totalScore = _uiState.value.totalScore.plus(correctGuessPoints),
                    isGuessCorrect = true,
                    isError = false
                )
            } else {
                currentState.copy(
                    isGuessCorrect = false, isError = true
                )
            }
        }
        if (_uiState.value.isGuessCorrect) {
            if (_uiState.value.currentWordCount == maxWordCount) {
                showDialog = true
            } else {
                selectRandomWord()
            }
        }
        resetGuess()
    }

    fun wordCount(): Boolean {
        return _uiState.value.currentWordCount < maxWordCount
    }

    fun resetGuess() {
        userGuess = ""
    }

    fun resetGame() {
        usedWords.clear()
        showDialog = false
        _uiState.update { currentState ->
            currentState.copy(
                currentWordCount = 0,
                totalScore = 0,
                isError = false,
                originalWord = "",
                isGuessCorrect = false
            )
        }
        selectRandomWord()
    }

}


