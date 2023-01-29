package com.koreatech.thunder.feature.thunder_add

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.feature.thunder.components.noRippleClickable
import java.util.Calendar

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ThunderAddScreen(
    navController: NavController = rememberNavController(),
    thunderAddViewModel: ThunderAddViewModel = hiltViewModel()
) {
    val titleText = thunderAddViewModel.titleText.collectAsStateWithLifecycle()
    val contentText = thunderAddViewModel.contentText.collectAsStateWithLifecycle()
    val hashtags = thunderAddViewModel.hashtags.collectAsStateWithLifecycle()
    val selectedHashtagCount =
        thunderAddViewModel.selectedHashtagCount.collectAsStateWithLifecycle()
    val limitParticipantsCnt =
        thunderAddViewModel.limitParticipantsCnt.collectAsStateWithLifecycle()
    val timeUiText = thunderAddViewModel.timeUiText.collectAsStateWithLifecycle()
    val dateUiText = thunderAddViewModel.dateUiText.collectAsStateWithLifecycle()
    val buttonState = thunderAddViewModel.buttonState.collectAsStateWithLifecycle()
    val (hour, minute) =
        thunderAddViewModel.hour24FormatTime.collectAsStateWithLifecycle()
            .value
            .split(":")
            .map { it.toInt() }
    val (year, month, dayOfMonth) =
        thunderAddViewModel.formattedText.collectAsStateWithLifecycle()
            .value
            .split("/")
            .map { it.toInt() }
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { view, _year, _month, _dayOfMonth ->
            thunderAddViewModel.setDate(_year, _month + 1, _dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    var isDateVisible by remember { mutableStateOf(false) }

    if (isDateVisible) {
        ThunderTimePicker(
            hour = hour,
            minute = minute,
            setTime = thunderAddViewModel::setTime,
            onDismissRequest = { isDateVisible = false }
        )
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ThunderToolBarSlot(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 12.dp, end = 26.dp),
            navigationIcon = {
                Image(
                    modifier = Modifier
                        .noRippleClickable { navController.popBackStack() },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = ""
                )
            },
            title = {
                Text(
                    text = "번개 생성하기",
                    style = ThunderTheme.typography.h3
                )
            },
            action = {
                Text(
                    modifier = Modifier
                        .clickable { /* 번개 추가하기 */ },
                    text = "완료",
                    style = ThunderTheme.typography.h5,
                    color = if (buttonState.value) Orange else Gray
                )
            }
        )
        Divider(modifier = Modifier.height(1.dp))
        BlankSpace(size = 12.dp)
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "카테고리 선택",
                    style = ThunderTheme.typography.h4
                )
                Text(
                    text = "${selectedHashtagCount.value}/4",
                    style = ThunderTheme.typography.b4,
                    color = Gray
                )
            }
            BlankSpace(size = 12.dp)
            ThunderChips(
                selectableHashtags = hashtags.value,
                isClickable = true,
                selectHashtag = thunderAddViewModel::selectHashtag
            )
            BlankSpace(size = 28.dp)
            Text(
                text = "제목",
                style = ThunderTheme.typography.h4
            )
            BlankSpace(size = 12.dp)
            ThunderTextField(
                text = titleText.value,
                hint = "제목을 입력하세요",
                isLimitTextCount = true,
                limitTextCount = 20,
                onTextChange = { text -> thunderAddViewModel.writeTitle(text) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
            BlankSpace(size = 8.dp)
            Text(
                text = "인원",
                style = ThunderTheme.typography.h4
            )
            BlankSpace(size = 12.dp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.noRippleClickable { thunderAddViewModel.minusLimitParticipantsCnt() },
                    painter = painterResource(id = R.drawable.ic_remove_count),
                    contentDescription = ""
                )
                Text(
                    text = "${limitParticipantsCnt.value}명",
                    style = ThunderTheme.typography.h4,
                    color = Orange
                )
                Image(
                    modifier = Modifier.noRippleClickable { thunderAddViewModel.plusLimitParticipantsCnt() },
                    painter = painterResource(id = R.drawable.ic_add_count),
                    contentDescription = ""
                )
            }
            BlankSpace(size = 12.dp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                Column {
                    Text(
                        text = "날짜",
                        style = ThunderTheme.typography.h4
                    )
                    BlankSpace(size = 12.dp)
                    Text(
                        modifier = Modifier
                            .noRippleClickable {
                                datePickerDialog.updateDate(year, month - 1, dayOfMonth)
                                datePickerDialog.show()
                            },
                        text = dateUiText.value,
                        style = ThunderTheme.typography.h4,
                        color = Orange
                    )
                }
                Column {
                    Text(
                        text = "시간",
                        style = ThunderTheme.typography.h4
                    )
                    BlankSpace(size = 12.dp)
                    Text(
                        modifier = Modifier
                            .noRippleClickable { isDateVisible = true },
                        text = timeUiText.value,
                        style = ThunderTheme.typography.h4,
                        color = Orange
                    )
                }
            }
            BlankSpace(size = 20.dp)
            Text(
                text = "내용",
                style = ThunderTheme.typography.h4
            )
            BlankSpace(size = 12.dp)
            ThunderTextField(
                text = contentText.value,
                hint = "장소, 만나서 할 활동에 대한 내용을 입력하세요",
                isLimitTextCount = true,
                limitTextCount = 150,
                onTextChange = { text -> thunderAddViewModel.writeContent(text) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ThunderAddScreenPreview() {
    ThunderTheme {
        ThunderAddScreen(thunderAddViewModel = ThunderAddViewModel(GetAllSelectableHashtagUseCase()))
    }
}
