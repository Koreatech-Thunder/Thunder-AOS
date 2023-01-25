package com.koreatech.thunder.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme

@Composable
fun ThunderTextField(
    text: String,
    hint: String,
    isLimitTextCount: Boolean = false,
    limitTextCount: Int = 0,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = { if (it.length <= limitTextCount) onTextChange(it) },
        textStyle = ThunderTheme.typography.b3,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(Orange)
    ) { innerTextField ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        color = Gray,
                        style = ThunderTheme.typography.b3
                    )
                }
                innerTextField()
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = if (text.isEmpty()) Gray else Orange
            )
            if (isLimitTextCount) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = "${text.length}/$limitTextCount",
                    style = ThunderTheme.typography.b4,
                    color = Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ThunderTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    val changeText: (input: String) -> Unit = { text = it }
    ThunderTheme {
        ThunderTextField(
            text = text,
            hint = "제목을 입력하세요",
            onTextChange = changeText,
            isLimitTextCount = true,
            limitTextCount = 20
        )
    }
}
