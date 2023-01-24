package com.koreatech.thunder.feature.thunder_add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ThunderAddScreen(
    thunderAddViewModel: ThunderAddViewModel = hiltViewModel()
) {
    val titleText = thunderAddViewModel.titleText.collectAsStateWithLifecycle()
    val contentText = thunderAddViewModel.titleText.collectAsStateWithLifecycle()
    val hashtags = thunderAddViewModel.hashtags.collectAsStateWithLifecycle()
    val selectedHashtagCount =
        thunderAddViewModel.selectedHashtagCount.collectAsStateWithLifecycle()
    val limitParticipantsCnt =
        thunderAddViewModel.limitParticipantsCnt.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ThunderToolBarSlot(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 12.dp, end = 26.dp),
            navigationIcon = {
                Image(
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
                    text = "완료",
                    style = ThunderTheme.typography.h5,
                    color = Orange
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
                onTextChange = thunderAddViewModel::writeTitle
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
                        text = "12.05 목요일",
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
                        text = "오전 9:00",
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
                onTextChange = thunderAddViewModel::writeContent
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
