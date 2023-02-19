package com.koreatech.thunder.feature.splash

import androidx.lifecycle.ViewModel
import com.koreatech.thunder.domain.usecase.GetSplashStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSplashStateUseCase: GetSplashStateUseCase
) : ViewModel() {
    fun getSplashState() = getSplashStateUseCase()
}
