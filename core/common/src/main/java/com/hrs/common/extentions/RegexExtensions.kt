package com.hrs.common.extentions

import android.util.Patterns

fun String.validateEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.validatePassword(): Boolean {
    val regex = "^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[*@!#%&()^~{}\\[\\]])" +
            "(?=\\S+$)" +
            ".{8,20}" +
            "$"
    return matches(regex.toRegex())
}

fun String.matches(regex: Regex, runner: String.() -> Unit) {
    if (matches(regex)) {
        runner.invoke(this)
    }
}

fun String.notMatches(regex: Regex, runner: String.() -> Unit) {
    if (!matches(regex)) {
        runner.invoke(this)
    }
}

fun String.groups(regex: String, runner: (groups: Array<String>) -> Unit) {
    val matcher = regex.toPattern().matcher(this)
    if (matcher.matches()) {
        val size = matcher.groupCount()
        var array = emptyArray<String>()
        for (i in 1..size) array += matcher.group(i)
        runner.invoke(array)
    }
}

fun String.matches(regex: String, runner: String.() -> Unit) = matches(regex.toRegex(), runner)

fun String.notMatches(regex: String, runner: String.() -> Unit) = notMatches(regex.toRegex(), runner)