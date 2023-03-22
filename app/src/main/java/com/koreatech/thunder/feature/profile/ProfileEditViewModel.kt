package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.Hashtag
import com.koreatech.thunder.domain.model.SelectableHashtag
import com.koreatech.thunder.domain.model.User
import com.koreatech.thunder.domain.usecase.GetAllSelectableHashtagUseCase
import com.koreatech.thunder.domain.usecase.GetUserProfileUseCase
import com.koreatech.thunder.domain.usecase.PutUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val getAllSelectableHashtagUseCase: GetAllSelectableHashtagUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val putUserProfileUseCase: PutUserProfileUseCase
) : ViewModel() {
    private val _user: MutableStateFlow<User> = MutableStateFlow(
        User(
            userId = "kwy",
            name = "kwy",
            introduction = "kwy",
            temperature = 0,
            hashtags = getAllSelectableHashtagUseCase(
                listOf(
                    SelectableHashtag(Hashtag.SPORT),
                    SelectableHashtag(Hashtag.WALK)
                )
            )
        )
    )
    private val cacheUser: MutableStateFlow<User> = MutableStateFlow(
        _user.value.copy()
    )
    val user = _user.asStateFlow()
    val buttonState = combine(user, cacheUser) { user, cacheUser ->
        user != cacheUser
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            getUserProfileUseCase()
                .onSuccess { user ->
                    _user.value = _user.value.copy(
                        userId = user.userId,
                        name = user.name,
                        introduction = user.introduction,
                        temperature = user.temperature,
                        hashtags = getAllSelectableHashtagUseCase(hashtags = user.hashtags)
                    )
                    cacheUser.value = _user.value.copy()
                }
                .onFailure { Timber.e("error ${it.message}") }
        }
    }

    fun selectHashtag(index: Int) {
        val afterHashtags = _user.value.hashtags.mapIndexed { idx, selectableHashtag ->
            if (idx == index) selectableHashtag.copy(isSelected = !selectableHashtag.isSelected)
            else selectableHashtag
        }
        _user.value = _user.value.copy(hashtags = afterHashtags)
    }

    fun writeNickname(nickname: String) {
        _user.value = _user.value.copy(name = nickname)
    }

    fun writeIntroduction(introduction: String) {
        _user.value = _user.value.copy(introduction = introduction)
    }

    fun putUserProfile() {
        viewModelScope.launch {
            putUserProfileUseCase(
                name = user.value.name,
                introduction = user.value.introduction,
                hashtags = user.value.hashtags.map { it.hashtag }
            )
        }
    }
}
