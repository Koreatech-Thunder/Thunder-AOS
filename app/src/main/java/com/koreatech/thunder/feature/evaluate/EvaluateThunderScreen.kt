package com.koreatech.thunder.feature.evaluate

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.components.noRippleClickable

@Composable
fun EvaluateThunderScreen(
    navController: NavController,
    thunderId: String,
    evaluateThunderViewModel: EvaluateThunderViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EvaluateThunderToolbar(navController = navController)
        EvaluateDescription("")
    }
}

@Composable
private fun EvaluateThunderToolbar(
    navController: NavController
) {
    ThunderToolBarSlot(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 18.dp),
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
                text = stringResource(R.string.evaluate_title),
                style = ThunderTheme.typography.h3
            )
        },
        action = {
            Text(
                modifier = Modifier
                    .clickable {
                        /* 평가 완료 API */
                    },
                text = stringResource(R.string.thunder_report_complete),
                style = ThunderTheme.typography.h5,
                color = Orange
            )
        }
    )
    Divider(modifier = Modifier.height(1.dp))
}

@Composable
private fun EvaluateDescription(
    title: String
) {
    Text(
        text = "이번 번개는 어떠셨나요?",
        style = ThunderTheme.typography.h2
    )
    Text(
        text = "\"${title}\"",
        style = ThunderTheme.typography.h3
    )
    Text(
        text = "번개는 잘 즐기셨나요?\n이번 번개에 대한 리뷰를 남겨주세요!",
        textAlign = TextAlign.Center,
        style = ThunderTheme.typography.h5
    )
}

@Composable
private fun EvaluateMemberItem(
    modifier: Modifier,
    evaluateMember: EvaluateMember
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = evaluateMember.nickname)
    }
}
