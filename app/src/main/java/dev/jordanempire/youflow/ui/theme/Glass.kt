package dev.jordanempire.youflow.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/** True when the glassmorphism look is active (dark theme, not pure black). */
val LocalGlass = compositionLocalOf { false }

object GlassTokens {
    val Base = Color(0xFF0B0B1A)
    val Violet = Color(0xFF7C4DFF)
    val Pink = Color(0xFFFF4081)
    val Cyan = Color(0xFF00E5FF)
}

/** Translucent frosted panel: soft white gradient fill with a thin light border. */
fun Modifier.glass(
    shape: Shape = RoundedCornerShape(24.dp),
    alpha: Float = 0.10f
): Modifier = this
    .clip(shape)
    .background(
        Brush.linearGradient(
            listOf(Color.White.copy(alpha = alpha + 0.06f), Color.White.copy(alpha = alpha * 0.5f))
        )
    )
    .border(
        1.dp,
        Brush.linearGradient(listOf(Color.White.copy(alpha = 0.35f), Color.White.copy(alpha = 0.05f))),
        shape
    )

/** Dark gradient with soft colour glows; the glass panels sit on top of it. */
@Composable
fun GlassBackdrop(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(GlassTokens.Base)
            .drawBehind {
                drawRect(
                    Brush.radialGradient(
                        listOf(GlassTokens.Violet.copy(alpha = 0.50f), Color.Transparent),
                        center = Offset(size.width * 0.1f, size.height * 0.08f),
                        radius = size.width * 0.95f
                    )
                )
                drawRect(
                    Brush.radialGradient(
                        listOf(GlassTokens.Pink.copy(alpha = 0.28f), Color.Transparent),
                        center = Offset(size.width * 0.95f, size.height * 0.45f),
                        radius = size.width * 0.8f
                    )
                )
                drawRect(
                    Brush.radialGradient(
                        listOf(GlassTokens.Cyan.copy(alpha = 0.22f), Color.Transparent),
                        center = Offset(size.width * 0.2f, size.height * 0.95f),
                        radius = size.width * 0.85f
                    )
                )
            }
    )
}
