package com.koreatech.thunder.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.koreatech.thunder.R

enum class ThunderDestination(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
) {
    THUNDER(
        icon = R.drawable.ic_launcher_foreground,
        title = R.string.bot_thunder,
        description = R.string.bot_thunder
    ),
    CHAT(
        icon = R.drawable.ic_launcher_foreground,
        title = R.string.bot_chat,
        description = R.string.bot_chat
    ),
    PROFILE(
        icon = R.drawable.ic_launcher_foreground,
        title = R.string.bot_profile,
        description = R.string.bot_profile
    )
}
