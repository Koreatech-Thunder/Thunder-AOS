package com.koreatech.thunder.feature.profile

import androidx.lifecycle.ViewModel
import com.koreatech.thunder.domain.model.Thunder
import com.koreatech.thunder.domain.model.dummyThunders
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThunderRecordViewModel @Inject constructor() : ViewModel() {
    private val _thunderRecord: MutableStateFlow<List<Thunder>> = MutableStateFlow(dummyThunders)
    val thunderRecord = _thunderRecord.asStateFlow()
}
