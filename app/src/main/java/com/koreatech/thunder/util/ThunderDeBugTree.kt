package com.koreatech.thunder.util

import timber.log.Timber

class ThunderDeBugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "${element.fileName} : ${element.lineNumber} - ${element.methodName}"
    }
}
