package com.koreatech.thunder.designsystem.style

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.koreatech.thunder.R

private val pretendardBold = FontFamily(
    Font(
        R.font.pretendard_bold,
        FontWeight.Bold,
        FontStyle.Normal
    )
)

private val pretendardSemiBold = FontFamily(
    Font(
        R.font.pretendard_semibold,
        FontWeight.SemiBold,
        FontStyle.Normal
    )
)

private val pretendardRegular = FontFamily(
    Font(
        R.font.pretendard_regular,
        FontWeight.Medium,
        FontStyle.Normal
    )
)

@Stable
class ThunderTypography internal constructor(
    h1: TextStyle,
    h2: TextStyle,
    h3: TextStyle,
    h4: TextStyle,
    h5: TextStyle,
    h6: TextStyle,
    b1: TextStyle,
    b2: TextStyle,
    b3: TextStyle,
    b4: TextStyle,
    b5: TextStyle
) {
    var h1: TextStyle by mutableStateOf(h1)
        private set
    var h2: TextStyle by mutableStateOf(h2)
        private set
    var h3: TextStyle by mutableStateOf(h3)
        private set
    var h4: TextStyle by mutableStateOf(h4)
        private set
    var h5: TextStyle by mutableStateOf(h5)
        private set
    var h6: TextStyle by mutableStateOf(h6)
        private set
    var b1: TextStyle by mutableStateOf(b1)
        private set
    var b2: TextStyle by mutableStateOf(b2)
        private set
    var b3: TextStyle by mutableStateOf(b3)
        private set
    var b4: TextStyle by mutableStateOf(b4)
        private set
    var b5: TextStyle by mutableStateOf(b5)
        private set

    fun copy(
        h1: TextStyle = this.h1,
        h2: TextStyle = this.h2,
        h3: TextStyle = this.h3,
        h4: TextStyle = this.h4,
        h5: TextStyle = this.h5,
        h6: TextStyle = this.h6,
        b1: TextStyle = this.b1,
        b2: TextStyle = this.b2,
        b3: TextStyle = this.b3,
        b4: TextStyle = this.b4,
        b5: TextStyle = this.b5
    ): ThunderTypography = ThunderTypography(h1, h2, h3, h4, h5, h6, b1, b2, b3, b4, b5)

    fun update(other: ThunderTypography) {
        h1 = other.h1
        h2 = other.h2
        h3 = other.h3
        h4 = other.h4
        h5 = other.h5
        h6 = other.h6
        b1 = other.b1
        b2 = other.b2
        b3 = other.b3
        b4 = other.b4
        b5 = other.b5
    }
}

@Composable
fun ThunderTypography(): ThunderTypography {
    return ThunderTypography(
        h1 = TextStyle(
            fontFamily = pretendardBold,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        ),
        h2 = TextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        ),
        h3 = TextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        ),
        h4 = TextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        h5 = TextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 20.sp
        ),
        h6 = TextStyle(
            fontFamily = pretendardSemiBold,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        ),
        b1 = TextStyle(
            fontFamily = pretendardRegular,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        ),
        b2 = TextStyle(
            fontFamily = pretendardRegular,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        ),
        b3 = TextStyle(
            fontFamily = pretendardRegular,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp
        ),
        b4 = TextStyle(
            fontFamily = pretendardRegular,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        ),
        b5 = TextStyle(
            fontFamily = pretendardRegular,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    )
}
