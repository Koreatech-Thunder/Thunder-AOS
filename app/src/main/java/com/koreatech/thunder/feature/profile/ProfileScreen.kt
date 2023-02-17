package com.koreatech.thunder.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.RoundedLinearIndicator
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderRowSpaceBetweenSlot
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Gray200
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.feature.thunder.components.noRippleClickable
import com.koreatech.thunder.navigation.ThunderDestination

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val user = profileViewModel.user.collectAsStateWithLifecycle()
    var isLogOutDialogVisible by remember { mutableStateOf(false) }
    var isWithDrawDialogVisible by remember { mutableStateOf(false) }

    if (isLogOutDialogVisible) {
        LogoutAlertDialog(
            onConfirmRequest = {},
            onDismissRequest = { isLogOutDialogVisible = false }
        )
    }
    if (isWithDrawDialogVisible) {
        WithDrawDialog(
            userName = user.value.name,
            onDismissRequest = { isWithDrawDialogVisible = false }
        )
    }
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        ProfileToolBar()
        BlankSpace(size = 12.dp)
        UserDetail(
            user = user.value,
            toProfileEdit = { navController.navigate(ThunderDestination.PROFILE_EDIT.name) }
        )
        BlankSpace(size = 20.dp)
        ServiceSettings(
            toAlarmSetting = { navController.navigate(ThunderDestination.ALARM_SETTING.name) },
            toThunderRecord = { navController.navigate(ThunderDestination.THUNDER_RECORD.name) }
        )
        ServiceManages()
        BlankSpace(size = 20.dp)
        Text(
            modifier = Modifier
                .padding(start = 16.dp)
                .noRippleClickable {
                    isLogOutDialogVisible = true
                },
            text = stringResource(R.string.profile_logout),
            style = ThunderTheme.typography.b2
        )
        BlankSpace(size = 16.dp)
        Text(
            modifier = Modifier
                .padding(start = 16.dp)
                .noRippleClickable {
                    isWithDrawDialogVisible = true
                },
            text = stringResource(R.string.profile_withdraw),
            textDecoration = TextDecoration.Underline,
            color = Gray,
            style = ThunderTheme.typography.b3
        )
    }
}

@Composable
private fun ProfileToolBar() {
    ThunderToolBarSlot(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 20.dp, bottom = 16.dp),
        title = {
            Text(
                text = stringResource(R.string.profile_tool_bar),
                style = ThunderTheme.typography.h3
            )
        }
    )
    Divider(modifier = Modifier.height(1.dp))
}

@Composable
private fun UserDetail(
    user: User,
    toProfileEdit: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        ThunderRowSpaceBetweenSlot(
            verticalAlignment = Alignment.Top,
            prefixComponent = {
                ProfileDetail(user)
            },
            postfixComponent = {
                Text(
                    modifier = Modifier
                        .clickable {
                            toProfileEdit()
                        }
                        .padding(4.dp),
                    text = stringResource(R.string.edit_thunder),
                    style = ThunderTheme.typography.b3
                )
            }
        )
        BlankSpace(size = 12.dp)
        Text(
            text = user.introduction,
            style = ThunderTheme.typography.h5
        )
        BlankSpace(size = 4.dp)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "나쁘지 않아요!",
                style = ThunderTheme.typography.h6
            )
            Text(
                text = "${user.temperature}",
                style = ThunderTheme.typography.h6
            )
        }
        BlankSpace(size = 12.dp)
        RoundedLinearIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp)
                .height(8.dp),
            progress = 0.60f,
            trackColor = Orange,
            backgroundColor = Orange200
        )
        BlankSpace(size = 12.dp)
        ThunderChips(selectableHashtags = user.hashtags)
    }
}

@Composable
private fun ProfileDetail(userUi: User) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(65.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_smile_faces),
                contentDescription = ""
            )
        }
        BlankSpace(size = 20.dp)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = userUi.name,
                style = ThunderTheme.typography.h3
            )
            Text(
                text = userUi.userId,
                style = ThunderTheme.typography.h6
            )
        }
    }
}

@Composable
private fun ServiceSettings(
    toAlarmSetting: () -> Unit,
    toThunderRecord: () -> Unit
) {
    SettingItemSlot(
        settingTitle = stringResource(R.string.profile_service_setting_title),
        firstSetting = {
            SettingTextItem(
                modifier = Modifier
                    .noRippleClickable {
                        toAlarmSetting()
                    },
                settingText = stringResource(R.string.profile_alarm_setting)
            )
        },
        secondSetting = {
            SettingTextItem(
                modifier = Modifier
                    .noRippleClickable {
                        toThunderRecord()
                    },
                settingText = stringResource(R.string.profile_participated_thunders)
            )
        }
    )
}

@Composable
private fun ServiceManages() {
    SettingItemSlot(
        settingTitle = stringResource(R.string.profile_service_manage),
        firstSetting = {
            SettingTextItem(
                modifier = Modifier
                    .noRippleClickable { },
                settingText = stringResource(R.string.profile_feedback)
            )
        },
        secondSetting = {
            SettingTextItem(
                modifier = Modifier
                    .noRippleClickable { },
                settingText = stringResource(R.string.profile_app_information)
            )
        }
    )
}

@Composable
private fun SettingItemSlot(
    settingTitle: String,
    firstSetting: @Composable () -> Unit,
    secondSetting: @Composable () -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray200)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            text = settingTitle,
            style = ThunderTheme.typography.b2
        )
        firstSetting()
        secondSetting()
    }
}

@Composable
private fun SettingTextItem(
    modifier: Modifier,
    settingText: String
) {
    Column {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = settingText,
            style = ThunderTheme.typography.b2
        )
        Divider(modifier = Modifier.height(1.dp))
    }
}
