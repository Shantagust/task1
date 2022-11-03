package com.example.shppproject.utils

class Validators {

    fun isMailCorrect(mail: String): Boolean {
        return (android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches() &&
                checkPointPosition(mail.split("@").first()))
    }

    fun isPasswordCorrect(password: String): Boolean {
        return (password.contains('@') or password.contains("$"))
    }

    private fun checkPointPosition(contains: String): Boolean {
        return (('.' in contains) &&
                (contains.indexOf('.') != 0 && contains.indexOf('.') != contains.length - 1))
    }
}