package com.koreatech.thunder.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderRowSpaceBetweenSlot
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.components.noRippleClickable

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AlarmSettingScreen(
    navController: NavController,
    alarmSettingViewModel: AlarmSettingViewModel = hiltViewModel()
) {
    val alarms = alarmSettingViewModel.alarms.collectAsStateWithLifecycle()
    Column() {
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
                    text = stringResource(R.string.alarm_setting_title),
                    style = ThunderTheme.typography.h3
                )
            }
        )
        Divider(modifier = Modifier.height(1.dp))
        AlarmSettingItem(
            alarmText = stringResource(R.string.thunder_alarm),
            isChecked = alarms.value[1],
            onClick = { alarmSettingViewModel.clickAlarm(0) }
        )
        Divider(modifier = Modifier.height(1.dp))
        AlarmSettingItem(
            alarmText = stringResource(R.string.evaluation_alarm),
            isChecked = alarms.value[1],
            onClick = { alarmSettingViewModel.clickAlarm(1) }
        )
        Divider(modifier = Modifier.height(1.dp))
        AlarmSettingItem(
            alarmText = stringResource(R.string.chat_alarm),
            isChecked = alarms.value[2],
            onClick = { alarmSettingViewModel.clickAlarm(2) }
        )
        Divider(modifier = Modifier.height(1.dp))
    }
}

@Composable
private fun AlarmSettingItem(
    alarmText: String,
    isChecked: Boolean,
    onClick: () -> Unit
) {
    ThunderRowSpaceBetweenSlot(
        modifier = Modifier.padding(16.dp),
        prefixComponent = { Text(text = alarmText) },
        postfixComponent = {
            Switch(
                checked = isChecked,
                onCheckedChange = { onClick() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Orange,
                    uncheckedThumbColor = Gray
                )
            )
        }
    )
}
