package com.koreatech.thunder.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.koreatech.thunder.designsystem.components.ThunderTextField
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.*
import com.koreatech.thunder.domain.model.ProfileType
import com.koreatech.thunder.feature.thunder.components.noRippleClickable
import com.koreatech.thunder.navigation.ThunderDestination
import com.koreatech.thunder.util.getIcon
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileEditScreen(
    navController: NavController = rememberNavController(),
    profileEditViewModel: ProfileEditViewModel = hiltViewModel()
) {
    val user = profileEditViewModel.user.collectAsStateWithLifecycle()
    val buttonState = profileEditViewModel.buttonState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(true) {
        profileEditViewModel.moveDestination
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .onEach { destination ->
                when (destination) {
                    ThunderDestination.PROFILE -> navController.popBackStack()
                    else -> {}
                }
            }
            .launchIn(lifecycleOwner.lifecycleScope)
    }

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
                    modifier = Modifier
                        .clickable(buttonState.value) {
                            profileEditViewModel.putUserProfile()
                        },
                    text = stringResource(R.string.profile_edit_btn),
                    style = ThunderTheme.typography.h5,
                    color = if (buttonState.value) Orange else Orange200
                )
            }
        )
        Divider(modifier = Modifier.height(1.dp))
        BlankSpace(size = 24.dp)

        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = "대표 이미지 설정",
            style = ThunderTheme.typography.h3
        )
        BlankSpace(size = 16.dp)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(ProfileType.values()) { profile ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, Orange200, RoundedCornerShape(4.dp))
                        .height(IntrinsicSize.Min)
                        .width(IntrinsicSize.Min),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .clickable(user.value.profile != profile) {
                                profileEditViewModel.selectProfile(profile)
                            }
                            .padding(8.dp),
                        painter = painterResource(id = profile.getIcon()),
                        contentDescription = ""
                    )
                    if (user.value.profile == profile) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    if (user.value.profile == profile) Orange200.copy(alpha = 0.8f)
                                    else Color.White
                                ),
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = ""
                        )
                    }
                }
            }
        }

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
            selectHashtag = profileEditViewModel::selectHashtag,
            isClickable = true
        )
    }
}
