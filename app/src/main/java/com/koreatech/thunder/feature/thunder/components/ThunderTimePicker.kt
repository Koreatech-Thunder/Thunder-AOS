package com.koreatech.thunder.feature.thunder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.koreatech.thunder.R
import com.koreatech.thunder.designsystem.components.BlankSpace
import com.koreatech.thunder.designsystem.components.ThunderDialogSlot
import com.koreatech.thunder.designsystem.style.Gray
import com.koreatech.thunder.designsystem.style.Orange
import com.koreatech.thunder.designsystem.style.ThunderTheme
import java.time.LocalTime

@Composable
fun ThunderTimePicker(
    hour: Int,
    minute: Int,
    setTime: (String) -> Unit,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties()
) {
    var snappedTimeText by remember { mutableStateOf("") }
    ThunderDialogSlot(
        radius = 20.dp,
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .width(IntrinsicSize.Min),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = stringResource(R.string.thunder_time),
                style = ThunderTheme.typography.h3
            )
            WheelTimePicker(
                startTime = LocalTime.of(hour, minute),
                modifier = Modifier.padding(horizontal = 64.dp),
                timeFormat = TimeFormat.AM_PM
            ) { snappedTime ->
                snappedTimeText = snappedTime.toString()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier
                        .noRippleClickable { onDismissRequest() },
                    color = Gray,
                    text = stringResource(R.string.thunder_cancel),
                    style = ThunderTheme.typography.h5
                )
                BlankSpace(size = 24.dp)
                Text(
                    modifier = Modifier
                        .noRippleClickable {
                            setTime(snappedTimeText)
                            onDismissRequest()
                        },
                    color = Orange,
                    text = stringResource(R.string.thunder_confirm),
                    style = ThunderTheme.typography.h5
                )
            }
        }
    }
}
