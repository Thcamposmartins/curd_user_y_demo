package com.estudo.yami_demo.middleware

import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

class DateUtils {
    fun formatBirthDate(birthDate: List<Int>): String {
        val localDateTime = LocalDateTime.of(
            birthDate[0], birthDate[1], birthDate[2],
            birthDate[3], birthDate[4], birthDate[5]
        )
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return localDateTime.format(formatter)
    }

    companion object {
        fun calculateAge(birthDate: LocalDateTime): Int {
            val now = LocalDateTime.now()
            val age = Period.between(birthDate.toLocalDate(), now.toLocalDate()).years
            return age
        }
    }
}