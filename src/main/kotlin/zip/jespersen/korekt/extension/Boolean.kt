package zip.jespersen.korekt.extension

fun ifNull(expression: Any?): Boolean {
    return expression == null
}

fun ifNull(expression: Any?, callback: () -> Unit) {
    if (expression == null)
        callback()
}

fun throwIfNull(expression: Any?, message: String? = null) {
    if (expression == null)
        throw NullPointerException(message)
}

fun ifNotNull(expression: Any?) : Boolean{
    return expression != null
}

fun ifNotNull(expression: Any?, callback: () -> Unit) {
    if (expression != null)
        callback()
}

