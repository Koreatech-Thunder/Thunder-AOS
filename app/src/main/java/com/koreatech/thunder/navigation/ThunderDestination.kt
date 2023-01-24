package com.koreatech.thunder.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.koreatech.thunder.R

enum class ThunderDestination(
    @DrawableRes val icon: Int = 0,
    @StringRes val title: Int = 0,
    @StringRes val description: Int = 0
) {
    THUNDER(
        icon = R.drawable.ic_thunder,
        title = R.string.bot_thunder,
        description = R.string.bot_thunder
    ),
    CHAT(
        icon = R.drawable.ic_chat,
        title = R.string.bot_chat,
        description = R.string.bot_chat
    ),
    PROFILE(
        icon = R.drawable.ic_person,
        title = R.string.bot_profile,
        description = R.string.bot_profile
    ),
    ADD
}
