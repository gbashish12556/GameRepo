package com.example.android.architecture.blueprints.machinecodingporoject.utils

fun String.replaceCharWithHyphen(index:Int): String {
    if (this.length >= index) {
        val modifiedString = StringBuilder(this)
        modifiedString.setCharAt(index, '-')
        return modifiedString.toString()
    }
    return this
}
