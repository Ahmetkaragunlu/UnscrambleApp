package com.ahmetkaragunlu.unscrambleapp

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmetkaragunlu.unscrambleapp.components.GameButtons
import com.ahmetkaragunlu.unscrambleapp.components.GameLayout
import com.ahmetkaragunlu.unscrambleapp.components.GameStatus
import com.ahmetkaragunlu.unscrambleapp.viewmodel.GameViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel(),
    ) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.unscramble),
            style = MaterialTheme.typography.titleLarge,
        )
        GameLayout(
            value = viewModel.userGuess,
            onValueChanged = { viewModel.updateUserGuess(it) },
            currentScrambleWord = uiState.currentScrambleWord,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            currentWordCount = uiState.currentWordCount,
            isError = uiState.isError
        )
        GameButtons(
            skipWords = { viewModel.selectRandomWord() },
            enabled = viewModel.wordCount(),
            submit = { viewModel.checkUserGuess() },
            resetGuess = { viewModel.resetGuess() }
        )
        GameStatus(
            totalScore = uiState.totalScore
        )
        ShowDialog(showDialog = viewModel.showDialog,
            totalScore = uiState.totalScore,
            resetGame = { viewModel.resetGame() }
        )
    }
}

@Composable
fun ShowDialog(
    showDialog: Boolean,
    totalScore: Int,
    resetGame: () -> Unit
) {
    val activity = (LocalContext.current as Activity)

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = stringResource(R.string.congratulations)) },
            text = { Text(text = stringResource(R.string.you_scored, totalScore)) },
            confirmButton = {
                TextButton(onClick = { activity.finish()
                                      resetGame()}) {
                    Text(stringResource(R.string.exit))
                }
            },
            dismissButton = {
                TextButton(onClick = { resetGame() }) {
                    Text(stringResource(R.string.play_again))
                }
            }
        )
    }
}

