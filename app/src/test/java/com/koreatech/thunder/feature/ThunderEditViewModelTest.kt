package com.koreatech.thunder.feature

import com.koreatech.thunder.domain.repository.ThunderRepository
import com.koreatech.thunder.domain.usecase.EditThunderUseCase
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.GetThunderUseCase
import com.koreatech.thunder.feature.thunder.base.InputUiState
import com.koreatech.thunder.feature.thunder.edit.ThunderEditViewModel
import com.koreatech.thunder.util.CoroutinesTestExtension
import com.koreatech.thunder.util.getPrivateProperty
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestExtension::class)
class ThunderEditViewModelTest {
    private lateinit var thunderEditViewModel: ThunderEditViewModel
    private val thunderRepository: ThunderRepository = mockk()
    private val getAllSelectableHashtagUseCase = GetAllSelectableHashtagUseCase()
    private val getThunderUseCase = GetThunderUseCase(thunderRepository)
    private val editThunderUseCase = EditThunderUseCase(thunderRepository)

    @BeforeEach
    fun setUp() {
        thunderEditViewModel = ThunderEditViewModel(
            getThunderUseCase,
            editThunderUseCase,
            getAllSelectableHashtagUseCase
        )
    }

    @DisplayName("번개 detail 에서 하나라도 수정 사항이 생기면 완료 버튼이 활성화 된다.")
    @Test
    fun buttonStateTest() = runTest {
        val collectJob =
            launch(UnconfinedTestDispatcher()) { thunderEditViewModel.buttonState.collect() }
        assertEquals(thunderEditViewModel.buttonState.value, false)
        val cacheUiState =
            thunderEditViewModel.getPrivateProperty<ThunderEditViewModel, MutableStateFlow<InputUiState>>(
                "cacheUiState"
            )

        assertEquals(thunderEditViewModel.uiState.value.title, cacheUiState!!.value.title)
        assertEquals(thunderEditViewModel.uiState.value.content, cacheUiState.value.content)
        assertEquals(thunderEditViewModel.uiState.value.date, cacheUiState.value.date)
        assertEquals(thunderEditViewModel.uiState.value.time, cacheUiState.value.time)

        thunderEditViewModel.selectHashtag(0)

        assertEquals(thunderEditViewModel.buttonState.value, true)

        collectJob.cancel()
    }
}
