package com.relayapp.live.core.extesion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
* Utilities for multiplying a list with an integer scalar, resulting
* in a larger list that repeats the original list.
*
*/

operator fun <E> List<E>.times(times: Int): List<E> =
    repeatListInternal(list = this, times)

operator fun <E> Int.times(list: List<E>): List<E> =
    repeatListInternal(list, times = this)

private fun <E> repeatListInternal(
    list: List<E>,
    times: Int
): List<E> {
    require(times > 0) { "Multiplying factor must be 1 or above. It was: $times" }

    if (times == 1 || list.isEmpty()) return list // fast path

    val result = list.toMutableList()
    repeat(times - 1) {
        result += list
    }

    return result
}

fun Modifier.thenWhen(
    logic: @Composable Modifier.() -> Modifier?
): Modifier = composed {
    this.logic() ?: this
}

fun <T> springBounce(
    stiffness: Float = 500f,
) = spring<T>(
    dampingRatio = 0.75f,
    stiffness = stiffness,
)

@RequiresApi(Build.VERSION_CODES.O)
fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.15f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 16.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 8.dp
) = this.drawBehind {
    val transparentColor = android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
    val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}