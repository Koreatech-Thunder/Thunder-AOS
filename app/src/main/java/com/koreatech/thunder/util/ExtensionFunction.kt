package com.koreatech.thunder.util

import com.koreatech.thunder.R
import com.koreatech.thunder.domain.model.ProfileType

fun ProfileType.getIcon() =
    when (this) {
        ProfileType.RAIN -> R.drawable.ic_freezing_rain
        ProfileType.WEAT -> R.drawable.ic_severe_weat
        ProfileType.WEATHER -> R.drawable.ic_severe_weather
        ProfileType.STORM -> R.drawable.ic_snow_storm_128
        ProfileType.THUNDER -> R.drawable.ic_thunder_cloud
    }