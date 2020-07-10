package org.itstep.liannoi.weatherforecast.infrastructure

import org.itstep.liannoi.weatherforecast.application.common.interfaces.Formatter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeFormatter(private val pattern: String) :
    Formatter {
    constructor() : this("yyyy-MM-dd HH:mm:ss")

    override fun format(dateTime: String): LocalDateTime {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern))
    }

    override fun format(dateTime: String, pattern: String): LocalDateTime {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern))
    }
}
