package com.ahmetkaragunlu.unscrambleapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.ahmetkaragunlu.unscrambleapp.R

@Composable
fun GameStatus(
    modifier: Modifier = Modifier,
    totalScore: Int
) {
    Card(modifier.padding(dimensionResource(R.dimen.large_padding)), shape = RectangleShape) {
        Text(
            text = stringResource(R.string.score, totalScore),
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier.padding(dimensionResource(R.dimen.medium_padding))
        )
    }
}