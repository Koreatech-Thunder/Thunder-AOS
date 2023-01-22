package com.koreatech.thunder.feature.thunder_add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderChips
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.domain.model.Hashtag

@Composable
fun ThunderAddScreen() {
    Column {
        ThunderToolBarSlot(
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_thunder),
                    contentDescription = ""
                )
            },
            title = {
                Text(text = "번개 생성하기")
            },
            action = {
                Text(text = "완료")
            }
        )
        Divider(modifier = Modifier.height(1.dp))
        Row {
            Text(text = "카테고리 선택")
            Text(text = "0/4")
        }
        ThunderChips(hashtags = Hashtag.values().toList())
        Text(text = "제목")
        TextField(value = "", onValueChange = {})
        Text(text = "인원")
        Row {
            Image(painter = painterResource(id = R.drawable.ic_thunder), contentDescription = "")
            Text(text = "5명")
            Image(painter = painterResource(id = R.drawable.ic_thunder), contentDescription = "")
        }
        Row {
            Column {
                Text(text = "날짜")
                Text(text = "12.05 목요일")
            }
            Column {
                Text(text = "시간")
                Text(text = "오전 9:00")
            }
        }
        Text(text = "내용")
        TextField(value = "", onValueChange = {})
    }
}
