package org.itstep.liannoi.weatherforecast.infrastructure

class CarefulCapitalize {
    fun String.careful(): String {
        return this.toLowerCase().capitalize()
    }
}
