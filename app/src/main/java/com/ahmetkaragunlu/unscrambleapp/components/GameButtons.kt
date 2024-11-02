package com.ahmetkaragunlu.unscrambleapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ahmetkaragunlu.unscrambleapp.R


@Composable
fun GameButtons(
    modifier: Modifier = Modifier,
    skipWords: () -> Unit,
    resetGuess: () -> Unit,
    submit: () -> Unit,
    enabled: Boolean,
) {
    Column(modifier = modifier.padding(dimensionResource(R.dimen.large_padding))) {
        Button(
            onClick = { submit() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceTint),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.submit))
        }
        Button(
            onClick = {
                skipWords()
                resetGuess()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceDim),
            enabled = enabled
        ) {
            Text(
                text = stringResource(R.string.skip),
                color = MaterialTheme.colorScheme.surfaceTint
            )
        }
    }
}