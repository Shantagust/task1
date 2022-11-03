package com.example.shppproject.utils

import java.util.*

// Parsing user name from mail
class Parser {

    fun parseName(unParsedName: String): Pair<String, String> {
        val (name, lastname) = unParsedName.split(".")
        return Pair(name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            lastname.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
    }

}