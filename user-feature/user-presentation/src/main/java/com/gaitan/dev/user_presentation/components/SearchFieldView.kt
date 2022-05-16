package com.gaitan.dev.user_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.gaitan.dev.core.R
import com.gaitan.dev.core_ui.utils.LocalSpacing

@Composable
fun SearchFieldView(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        TextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            maxLines = 1,
            label = {
                Text(text = stringResource(R.string.text_title_field_search))
            },
            keyboardActions = KeyboardActions(
                onSearch = {
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
        )
    }
}