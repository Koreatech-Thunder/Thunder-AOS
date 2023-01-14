package com.koreatech.thunder.designsystem.style

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalThunderTypography = staticCompositionLocalOf<ThunderTypography> {
    error("No ThunderTypography provided")
}

object ThunderTheme {
    val typography: ThunderTypography @Composable get() = LocalThunderTypography.current
}

@Composable
fun ProvideThunderTypography(
    typography: ThunderTypography,
    content: @Composable () -> Unit
) {
    val provideTypography = remember { typography.copy() }
    CompositionLocalProvider(
        LocalThunderTypography provides provideTypography,
        content = content
    )
}

@Composable
fun ThunderTheme(
    content: @Composable () -> Unit
) {
    val typography = ThunderTypography()
    ProvideThunderTypography(typography = typography) {
        MaterialTheme(content = content)
    }
}
