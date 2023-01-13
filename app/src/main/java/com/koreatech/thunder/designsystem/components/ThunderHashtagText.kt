package com.koreatech.thunder.designsystem.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.koreatech.thunder.feature.thunder.model.HashtagUi

@Composable
fun ThunderHashtagText(
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    color: Color,
    hashtagUi: HashtagUi
) {
    Text(
        modifier = modifier,
        color = color,
        text = "#${hashtagUi.hashtag}",
        style = textStyle
    )
}
