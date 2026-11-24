package dev.jordanempire.youflow.ui.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Fallback palette (seed: YouTube red #E53935), used when dynamic colour is switched off.
private val FallbackLight = lightColorScheme(
    primary = Color(0xFFB3261E),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDAD5),
    onPrimaryContainer = Color(0xFF410002),
    secondary = Color(0xFF775651),
    secondaryContainer = Color(0xFFFFDAD5),
    tertiary = Color(0xFF705C2E),
    background = Color(0xFFFFF8F7),
    surface = Color(0xFFFFF8F7)
)

private val FallbackDark = darkColorScheme(
    primary = Color(0xFFFFB4A8),
    onPrimary = Color(0xFF690003),
    primaryContainer = Color(0xFF93000A),
    onPrimaryContainer = Color(0xFFFFDAD5),
    secondary = Color(0xFFE7BDB6),
    secondaryContainer = Color(0xFF5D3F3B),
    tertiary = Color(0xFFDEC48C),
    background = Color(0xFF1A1110),
    surface = Color(0xFF1A1110)
)

// Glassmorphism palette: deep navy base with violet, cyan and pink accents.
private val GlassDark = darkColorScheme(
    primary = Color(0xFFB69DFF),
    onPrimary = Color(0xFF2A0F6B),
    primaryContainer = Color(0xFF4A2FA0),
    onPrimaryContainer = Color(0xFFE9DDFF),
    secondary = Color(0xFF8FE9FF),
    secondaryContainer = Color(0xFF1F4A57),
    tertiary = Color(0xFFFF8FB8),
    background = Color(0xFF0B0B1A),
    surface = Color(0xFF0B0B1A),
    surfaceDim = Color(0xFF0B0B1A),
    surfaceContainerLowest = Color(0xFF080814),
    surfaceContainerLow = Color(0xFF12122A),
    surfaceContainer = Color(0xFF181834),
    surfaceContainerHigh = Color(0xFF20203F),
    surfaceContainerHighest = Color(0xFF292950)
)

private fun colorSchemeFor(context: Context, dark: Boolean, dynamic: Boolean): ColorScheme = when {
    dynamic && dark -> dynamicDarkColorScheme(context)
    dynamic -> dynamicLightColorScheme(context)
    dark -> FallbackDark
    else -> FallbackLight
}

private fun ColorScheme.pureBlack() = copy(
    background = Color.Black,
    surface = Color.Black,
    surfaceDim = Color.Black,
    surfaceContainerLowest = Color.Black,
    surfaceContainerLow = Color(0xFF0A0A0A),
    surfaceContainer = Color(0xFF111111),
    surfaceContainerHigh = Color(0xFF181818),
    surfaceContainerHighest = Color(0xFF202020)
)

@Composable
fun YouFlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    pureBlack: Boolean = false,
    content: @Composable () -> Unit
) {
    // Glass look applies in dark mode; "pure black" keeps its plain black look.
    val glass = darkTheme && !pureBlack
    val scheme = if (glass) GlassDark else colorSchemeFor(LocalContext.current, darkTheme, dynamicColor)
    CompositionLocalProvider(LocalGlass provides glass) {
        MaterialExpressiveTheme(
            colorScheme = if (darkTheme && pureBlack) scheme.pureBlack() else scheme,
            motionScheme = MotionScheme.expressive(),
            content = content
        )
    }
}
