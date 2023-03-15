package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.dummyThunders
import com.koreatech.thunder.domain.usecase.GetThunderRecordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ThunderRecordViewModel @Inject constructor(
    private val getThunderRecordsUseCase: GetThunderRecordsUseCase
) : ViewModel() {
    private val _thunderRecord: MutableStateFlow<List<Thunder>> = MutableStateFlow(dummyThunders)
    val thunderRecord = _thunderRecord.asStateFlow()

    init {
//        viewModelScope.launch {
//            getThunderRecordsUseCase()
//                .onSuccess { thunderRecords ->
//                    _thunderRecord.value = thunderRecords
//                }
//                .onFailure { Timber.e("error ${it.message}") }
//        }
    }
}
