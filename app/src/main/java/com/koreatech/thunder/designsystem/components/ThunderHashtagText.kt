package com.koreatech.thunder.designsystem.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.koreatech.thunder.domain.model.Hashtag

@Composable
fun ThunderHashtagText(
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    color: Color,
    hashtag: Hashtag
) {
    Text(
        modifier = modifier,
        color = color,
        text = "#${hashtag.type}",
        style = textStyle
    )
}
