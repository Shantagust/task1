package com.example.shppproject.utils

import java.util.*

// Parsing user name from mail
class Parser {
    fun parseName(unParsedName: String?): Pair<String, String> {
        var (name, soname) = unParsedName!!.split("@")?.first().toString().split(".")
        name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        soname.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        return Pair(name, soname)
    }
}