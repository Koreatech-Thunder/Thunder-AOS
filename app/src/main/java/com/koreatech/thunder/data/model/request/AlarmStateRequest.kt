package com.koreatech.thunder.data.model.request

data class AlarmStateRequest(
    val thunderAlarm: Boolean?,
    val evaluateAlarm: Boolean?,
    val chatAlarm: Boolean?
)
