package com.koreatech.thunder.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
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
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.components.noRippleClickable

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ProfileEditScreen(
    navController: NavController = rememberNavController(),
    profileEditViewModel: ProfileEditViewModel = hiltViewModel()
) {
    val user = profileEditViewModel.user.collectAsStateWithLifecycle()
    val buttonState = profileEditViewModel.buttonState.collectAsStateWithLifecycle()

    Column {
        ThunderToolBarSlot(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 18.dp),
            navigationIcon = {
                Image(
                    modifier = Modifier.noRippleClickable { navController.popBackStack() },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = ""
                )
            },
            title = {
                Text(
                    text = stringResource(R.string.profile_title),
                    style = ThunderTheme.typography.h3
                )
            },
            action = {
                Text(
                    modifier = Modifier.clickable(buttonState.value) {
                        /* edit profile */
                    },
                    text = stringResource(R.string.profile_edit_btn),
                    style = ThunderTheme.typography.h5,
                    color = if (buttonState.value) Orange200 else Orange
                )
            }
        )
        Divider(modifier = Modifier.height(1.dp))
        BlankSpace(size = 24.dp)

        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = stringResource(R.string.profile_nickname),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 16.dp)
        ThunderTextField(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = user.value.name,
            hint = stringResource(R.string.profile_nickname_hint),
            onTextChange = profileEditViewModel::writeNickname
        )
        BlankSpace(size = 42.dp)

        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = stringResource(R.string.profile_introduce),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 16.dp)
        ThunderTextField(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = user.value.introduction,
            hint = stringResource(R.string.profile_introduce_hint),
            limitTextCount = 120,
            isLimitTextCount = true,
            onTextChange = profileEditViewModel::writeIntroduction
        )
        BlankSpace(size = 20.dp)

        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = stringResource(R.string.profile_hashtag_title),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 12.dp)
        ThunderChips(
            modifier = Modifier.padding(horizontal = 18.dp),
            selectableHashtags = user.value.hashtags,
            selectHashtag = profileEditViewModel::selectHashtag
        )
    }
}
