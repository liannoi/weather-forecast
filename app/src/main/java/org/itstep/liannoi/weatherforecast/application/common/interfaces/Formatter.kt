package org.itstep.liannoi.weatherforecast.application.common.interfaces

import java.time.LocalDateTime

@FunctionalInterface
interface Formatter {
    fun format(dateTime: String, pattern: String): LocalDateTime
    fun format(dateTime: String): LocalDateTime
}
