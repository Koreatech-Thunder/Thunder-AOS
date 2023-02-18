package com.koreatech.thunder.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.feature.thunder.components.ThunderTextHashtags
import com.koreatech.thunder.feature.thunder.components.noRippleClickable


@Composable
fun ThunderRecordScreen(
    navController: NavController,
    thunderRecordViewModel: ThunderRecordViewModel = hiltViewModel()
) {
    val thunderRecord = thunderRecordViewModel.thunderRecord.collectAsStateWithLifecycle()
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
                    text = stringResource(R.string.thunder_record_title),
                    style = ThunderTheme.typography.h3
                )
            }
        )
        Divider(modifier = Modifier.height(1.dp))
        LazyColumn {
            items(thunderRecord.value) {
                ThunderRecordItem(thunder = it)
                Divider(modifier = Modifier.height(1.dp))
            }
        }
    }
}

@Composable
private fun ThunderRecordItem(thunder: Thunder) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = thunder.title,
            style = ThunderTheme.typography.h4
        )
        Text(
            text = thunder.deadline,
            style = ThunderTheme.typography.h5,
            color = Gray
        )
        ThunderTextHashtags(thunder.hashtags)
    }
}
