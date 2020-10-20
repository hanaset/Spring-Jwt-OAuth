package com.rufree.dobi.common

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDateTime?.toSeoulEpochSecond(): Long? {
    return this?.toEpochSecond(ZoneOffset.of("+09:00"))
}

fun LocalDateTime.toDateString(): String {
    return this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun Date?.toSeoulEpochSecond(): Long? {
    return this?.toInstant()?.atZone(ZoneId.of("Asia/Seoul"))?.toEpochSecond()
}

fun Date?.toDateString(): String? {
    return LocalDateTime.ofInstant(this?.toInstant(), ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun<T> List<T>.paging(page: Int, pageSize: Int): List<T>? {
    val startIndex = pageSize * page
    val endIndex = startIndex + pageSize
    val size = this.size
    return when {
        startIndex >= size -> null
        startIndex < size && endIndex >= size -> ArrayList(this.subList(startIndex, size))
        else -> ArrayList(this.subList(startIndex, endIndex))
    }
}

fun String.money(): Long {
    return this.replace("₩","").replace(",","").toLong()
}