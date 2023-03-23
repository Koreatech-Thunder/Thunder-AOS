package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.usecase.GetThunderRecordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class ThunderRecordViewModel @Inject constructor(
    private val getThunderRecordsUseCase: GetThunderRecordsUseCase
) : ViewModel() {
    private val _thunderRecord: MutableStateFlow<List<Thunder>> = MutableStateFlow(emptyList())
    val thunderRecord = _thunderRecord.asStateFlow()

    init {
        viewModelScope.launch {
            getThunderRecordsUseCase()
                .onSuccess { thunderRecords ->
                    _thunderRecord.value = thunderRecords
                }
                .onFailure { Timber.e("error ${it.message}") }
        }
    }
}
