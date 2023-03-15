package com.koreatech.thunder.feature.thunder.base

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.components.ThunderTimePicker
import com.koreatech.thunder.feature.thunder.components.noRippleClickable
import java.util.Calendar

@Composable
fun ThunderInputScreen(
    screenTitle: String,
    uiState: InputUiState,
    navController: NavController,
    thunderInputViewModel: ThunderInputViewModel,
    localFocusManager: FocusManager = LocalFocusManager.current
) {
    val buttonState = thunderInputViewModel.buttonState.collectAsStateWithLifecycle()
    val (hour, minute) =
        thunderInputViewModel.hour24FormatTime.collectAsStateWithLifecycle()
            .value
            .split(":")
            .map { it.toInt() }
    val (year, month, dayOfMonth) =
        thunderInputViewModel.formattedText.collectAsStateWithLifecycle()
            .value
            .split("/")
            .map { it.toInt() }
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { view, _year, _month, _dayOfMonth ->
            thunderInputViewModel.setDate(_year, _month + 1, _dayOfMonth)
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
            setTime = thunderInputViewModel::setTime,
            onDismissRequest = { isDateVisible = false }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
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
                    text = screenTitle,
                    style = ThunderTheme.typography.h3
                )
            },
            action = {
                Text(
                    modifier = Modifier
                        .clickable { thunderInputViewModel.onClickThunder() },
                    text = stringResource(R.string.thunder_report_complete),
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
                    text = stringResource(R.string.thunder_add_hashtag_select),
                    style = ThunderTheme.typography.h4
                )
                Text(
                    text = "${uiState.selectedHashtagCount}/4",
                    style = ThunderTheme.typography.b4,
                    color = Gray
                )
            }
            BlankSpace(size = 12.dp)
            ThunderChips(
                selectableHashtags = uiState.hashtags,
                isClickable = true,
                selectHashtag = thunderInputViewModel::selectHashtag
            )
            BlankSpace(size = 28.dp)
            Text(
                text = stringResource(R.string.thunder_add_subtitle),
                style = ThunderTheme.typography.h4
            )
            BlankSpace(size = 12.dp)
            ThunderTextField(
                modifier = Modifier,
                text = uiState.title,
                hint = stringResource(R.string.thunder_add_title_hint),
                isLimitTextCount = true,
                limitTextCount = 20,
                onTextChange = thunderInputViewModel::writeTitle,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    localFocusManager.clearFocus()
                })
            )
            BlankSpace(size = 8.dp)
            Text(
                text = stringResource(R.string.thunder_add_members_cnt),
                style = ThunderTheme.typography.h4
            )
            BlankSpace(size = 12.dp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.noRippleClickable { thunderInputViewModel.minusLimitParticipantsCnt() },
                    painter = painterResource(id = R.drawable.ic_remove_count),
                    contentDescription = ""
                )
                Text(
                    text = "${uiState.limitParticipantsCnt}명",
                    style = ThunderTheme.typography.h4,
                    color = Orange
                )
                Image(
                    modifier = Modifier.noRippleClickable { thunderInputViewModel.plusLimitParticipantsCnt() },
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
                        text = stringResource(R.string.thunder_add_date),
                        style = ThunderTheme.typography.h4
                    )
                    BlankSpace(size = 12.dp)
                    Text(
                        modifier = Modifier
                            .noRippleClickable {
                                datePickerDialog.updateDate(year, month - 1, dayOfMonth)
                                datePickerDialog.show()
                            },
                        text = uiState.date,
                        style = ThunderTheme.typography.h4,
                        color = Orange
                    )
                }
                Column {
                    Text(
                        text = stringResource(R.string.thunder_add_time),
                        style = ThunderTheme.typography.h4
                    )
                    BlankSpace(size = 12.dp)
                    Text(
                        modifier = Modifier
                            .noRippleClickable { isDateVisible = true },
                        text = uiState.time,
                        style = ThunderTheme.typography.h4,
                        color = Orange
                    )
                }
            }
            BlankSpace(size = 20.dp)
            Text(
                text = stringResource(R.string.thunder_add_content),
                style = ThunderTheme.typography.h4
            )
            BlankSpace(size = 12.dp)
            ThunderTextField(
                modifier = Modifier,
                text = uiState.content,
                hint = stringResource(R.string.thunder_add_content_hint),
                isLimitTextCount = true,
                limitTextCount = 150,
                onTextChange = thunderInputViewModel::writeContent
            )
        }
    }
}
