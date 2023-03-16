package com.koreatech.thunder.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderCommonButton
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.SplashState
import com.koreatech.thunder.navigation.ThunderDestination
import com.koreatech.thunder.navigation.popAndMoveTo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun UserInputScreen(
    navController: NavController = rememberNavController(),
    userInputViewModel: UserInputViewModel = hiltViewModel(),
    localFocusManager: FocusManager = LocalFocusManager.current
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val hashtags = userInputViewModel.hashtags.collectAsStateWithLifecycle()
    val nickname = userInputViewModel.nickname.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        userInputViewModel.moveDestination
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .onEach { destination ->
                when (destination) {
                    ThunderDestination.ON_BOARDING -> {
                        userInputViewModel.setSplashState(SplashState.ON_BOARDING)
                        navController.popAndMoveTo(ThunderDestination.ON_BOARDING)
                    }
                    else -> {}
                }
            }
            .launchIn(lifecycleOwner.lifecycleScope)
    }

    Column(
        modifier = Modifier.padding(horizontal = 18.dp, vertical = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.user_input_title),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 64.dp)

        Text(
            text = stringResource(R.string.profile_nickname),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 24.dp)
        ThunderTextField(
            modifier = Modifier,
            text = nickname.value,
            hint = stringResource(R.string.profile_nickname_hint),
            onTextChange = userInputViewModel::writeNickname,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { localFocusManager.clearFocus() }
            )
        )
        BlankSpace(size = 30.dp)

        Text(
            text = stringResource(R.string.profile_hashtag_title),
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 8.dp)
        Text(
            text = stringResource(R.string.user_input_hashtag),
            color = Gray,
            style = ThunderTheme.typography.b3
        )
        BlankSpace(size = 16.dp)
        ThunderChips(
            modifier = Modifier.weight(1f),
            selectableHashtags = hashtags.value,
            isClickable = true,
            selectHashtag = userInputViewModel::selectHashtag
        )

        ThunderCommonButton(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(if (nickname.value.isNotEmpty()) Orange else Gray)
                .clickable(nickname.value.isNotEmpty()) {
                    userInputViewModel.putUserProfile()
                },
            buttonText = {
                Text(
                    text = stringResource(R.string.user_input_btn),
                    style = ThunderTheme.typography.h4,
                    color = Color.White
                )
            }
        )
    }
}
