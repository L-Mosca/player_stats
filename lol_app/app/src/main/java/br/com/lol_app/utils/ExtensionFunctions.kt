package br.com.lol_app.utils

import android.util.TypedValue
import android.view.ViewGroup

enum class LoadingType { MAIN_LOADING, SECONDARY_LOADING }

fun Int.toDpMetric(parent: ViewGroup): Int {
    val pixel = this

    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        pixel.toFloat(),
        parent.context.resources.displayMetrics
    ).toInt()
}
