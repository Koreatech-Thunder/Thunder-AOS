package com.koreatech.thunder.feature.evaluate

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderToolBarSlot
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.Orange200
import com.koreatech.thunder.designsystem.style.ThunderTheme
import com.koreatech.thunder.feature.thunder.components.noRippleClickable
import com.koreatech.thunder.navigation.ThunderDestination
import com.koreatech.thunder.navigation.popAndMoveTo
import com.koreatech.thunder.util.getIcon
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EvaluateThunderScreen(
    navController: NavController,
    thunderId: String,
    evaluateThunderViewModel: EvaluateThunderViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val uiState = evaluateThunderViewModel.uiState.collectAsStateWithLifecycle()
    val state = rememberPagerState()

    LaunchedEffect(true) {
        evaluateThunderViewModel.getEvaluateThunder(thunderId)

        evaluateThunderViewModel.moveDestination.flowWithLifecycle(lifecycleOwner.lifecycle)
            .onEach { destination ->
                when (destination) {
                    ThunderDestination.THUNDER -> navController.popAndMoveTo(ThunderDestination.THUNDER)
                    else -> {}
                }
            }.launchIn(lifecycleOwner.lifecycleScope)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EvaluateThunderToolbar(
            navController = navController,
            onClick = { evaluateThunderViewModel.putEvaluate(thunderId) }
        )
        BlankSpace(size = 36.dp)
        EvaluateDescription(uiState.value.title)
        EvaluateMembers(
            state = state,
            evaluateMembers = uiState.value.evaluateMembers,
            onClick = evaluateThunderViewModel::setMemberRating
        )
        HorizontalPagerIndicator(
            pagerState = state,
            activeColor = Orange,
            inactiveColor = Orange200
        )
        BlankSpace(size = 16.dp)
        MembersIndex(
            currentIndex = state.currentPage + 1,
            totalIndex = state.pageCount
        )
        BlankSpace(size = 24.dp)
    }
}

@Composable
private fun EvaluateThunderToolbar(
    navController: NavController,
    onClick: () -> Unit
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
                    .clickable { onClick() },
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
        text = stringResource(R.string.evaluate_content),
        style = ThunderTheme.typography.h2
    )
    BlankSpace(size = 12.dp)
    Text(
        text = "\"${title}\"",
        style = ThunderTheme.typography.h3
    )
    BlankSpace(size = 12.dp)
    Text(
        text = stringResource(R.string.evaluate_content2),
        textAlign = TextAlign.Center,
        style = ThunderTheme.typography.h5
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ColumnScope.EvaluateMembers(
    state: PagerState,
    evaluateMembers: List<EvaluateMember>,
    onClick: (Int, Int) -> Unit
) {
    HorizontalPager(
        modifier = Modifier.weight(1f),
        state = state,
        count = evaluateMembers.size,
        contentPadding = PaddingValues(32.dp)
    ) { page ->
        Surface(
            modifier = Modifier.padding(horizontal = 8.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(15.dp)
        ) {
            EvaluateMemberItem(
                modifier = Modifier.fillMaxSize(),
                memberIndex = page,
                evaluateMember = evaluateMembers[page],
                onClick = onClick
            )
        }
    }
}

@Composable
private fun EvaluateMemberItem(
    modifier: Modifier,
    memberIndex: Int,
    evaluateMember: EvaluateMember,
    onClick: (Int, Int) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = evaluateMember.nickname,
            style = ThunderTheme.typography.b1
        )
        Image(
            painter = painterResource(id = evaluateMember.profile.getIcon()),
            contentDescription = ""
        )
        Text(
            text = "${evaluateMember.rating.toDouble()}",
            style = ThunderTheme.typography.h3
        )
        RatingStars(
            memberIndex = memberIndex,
            currentRating = evaluateMember.rating,
            onClick = onClick
        )
    }
}

@Composable
private fun RatingStars(
    memberIndex: Int,
    currentRating: Int,
    onClick: (Int, Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(5) { idx ->
            RatingStar(
                memberIndex = memberIndex,
                currentRating = currentRating,
                targetRating = idx + 1,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun RatingStar(
    memberIndex: Int,
    currentRating: Int,
    targetRating: Int,
    onClick: (Int, Int) -> Unit
) {
    Image(
        modifier = Modifier.clickable {
            onClick(memberIndex, targetRating)
        },
        painter = painterResource(
            id = if (currentRating < targetRating) R.drawable.ic_star_empty
            else R.drawable.ic_star_fill
        ),
        contentDescription = ""
    )
}

@Composable
private fun MembersIndex(
    currentIndex: Int,
    totalIndex: Int
) {
    Text(
        text = "$currentIndex / $totalIndex",
        style = ThunderTheme.typography.b3
    )
}
