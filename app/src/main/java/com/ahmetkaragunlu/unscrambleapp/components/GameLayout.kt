package com.ahmetkaragunlu.unscrambleapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmetkaragunlu.unscrambleapp.R
import com.ahmetkaragunlu.unscrambleapp.data.maxWordCount

@Composable
fun GameLayout(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    currentScrambleWord: String,
    keyboardOptions: KeyboardOptions,
    currentWordCount: Int,
    isError: Boolean
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.large_padding))
            .height(300.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.small_padding))
    ) {
        EditWordCountText(currentWordCount = currentWordCount)

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentScrambleWord,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.height(dimensionResource(R.dimen.large_padding)))
            Text(
                text = stringResource(R.string.description),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = value,
                onValueChange = onValueChanged,
                label = {
                    Text(
                        text = if (isError) stringResource(R.string.guess_wrong) else stringResource(
                            R.string.label)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = keyboardOptions,
                isError = isError
            )
        }
    }
}

@Composable
private fun EditWordCountText(modifier: Modifier = Modifier, currentWordCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.word_count, currentWordCount, maxWordCount),
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .width(64.dp)
                .height(36.dp)
                .background(color = MaterialTheme.colorScheme.surfaceTint)
                .align(Alignment.End)
                .padding(top = 6.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

