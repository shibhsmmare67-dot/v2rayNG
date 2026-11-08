package com.v2ray.ang.ui.compose

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.v2ray.ang.AppConfig
import com.v2ray.ang.handler.MmkvManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private val LightColor = lightColorScheme(
    primary = Color(0xFF0E5B4A), // Deep Green (Bettbox-style accent)
    onPrimary = Color(0xFFFFFFFF), // White
    primaryContainer = Color(0xFFCDE8DD), // Pale Green
    onPrimaryContainer = Color(0xFF0E3B32), // Dark Green
    secondary = Color(0xFF0E5B4A), // Deep Green
    onSecondary = Color(0xFFFFFFFF), // White
    secondaryContainer = Color(0xFFDCEFE7), // Pale Green
    onSecondaryContainer = Color(0xFF0E3B32), // Dark Green
    tertiary = Color(0xFF0E7A5F), // Green
    onTertiary = Color(0xFFFFFFFF), // White
    tertiaryContainer = Color(0xFFCDE8DD), // Pale Green
    onTertiaryContainer = Color(0xFF0E3B32), // Dark Green
    error = Color(0xFFBA1A1A), // Red
    errorContainer = Color(0xFFFFDAD6), // Light Red
    onError = Color(0xFFFFFFFF), // White
    onErrorContainer = Color(0xFF410002), // Dark Red
    background = Color(0xFFE8EDE9), // Pale Gray-Green (Bettbox-style page background)
    onBackground = Color(0xFF1C2B26), // Near Black Green
    surface = Color(0xFFFFFFFF), // White
    onSurface = Color(0xFF1C2B26), // Near Black Green
    surfaceVariant = Color(0xFFE2EAE5), // Pale Gray-Green
    onSurfaceVariant = Color(0xFF4A5A54), // Dark Gray Green
    outline = Color(0xFFB9C8BE), // Gray Green
    outlineVariant = Color(0xFFD6E0D9), // Light Gray Green
    inverseSurface = Color(0xFF1E3A32), // Dark Green
    inverseOnSurface = Color(0xFFEAF2ED), // Very Light Gray-Green
    inversePrimary = Color(0xFFA8D5C2), // Light Green
    scrim = Color(0xFF000000), // Black
    surfaceTint = Color(0xFF0E5B4A), // Deep Green
    surfaceContainerLowest = Color(0xFFFFFFFF), // White
    surfaceContainerLow = Color(0xFFF4F7F5), // Very Light Gray-Green
    surfaceContainer = Color(0xFFEDF2EF), // Light Gray-Green
    surfaceContainerHigh = Color(0xFFE6EDE9), // Light Gray-Green
    surfaceContainerHighest = Color(0xFFDFE8E2), // Light Gray-Green
)

private val DarkColor = darkColorScheme(
    primary = Color(0xFFA8D5C2), // Light Green
    onPrimary = Color(0xFF0E3B32), // Dark Green
    primaryContainer = Color(0xFF0E5B4A), // Deep Green
    onPrimaryContainer = Color(0xFFCDE8DD), // Pale Green
    secondary = Color(0xFF7ED0B0), // Light Green
    onSecondary = Color(0xFF0E3B32), // Dark Green
    secondaryContainer = Color(0xFF0E5B4A), // Deep Green
    onSecondaryContainer = Color(0xFFCDE8DD), // Pale Green
    tertiary = Color(0xFF83D6B5), // Mint Green
    onTertiary = Color(0xFF00382E), // Dark Teal
    tertiaryContainer = Color(0xFF005143), // Teal
    onTertiaryContainer = Color(0xFFA0F2D0), // Light Green
    error = Color(0xFFFFB4AB), // Light Red
    errorContainer = Color(0xFF93000A), // Dark Red
    onError = Color(0xFF690005), // Deep Red
    onErrorContainer = Color(0xFFFFDAD6), // Light Red
    background = Color(0xFF1C1B1F), // Near Black
    onBackground = Color(0xFFE6E1E5), // Light Gray
    surface = Color(0xFF1C1B1F), // Near Black
    onSurface = Color(0xFFE6E1E5), // Light Gray
    surfaceVariant = Color(0xFF49454F), // Dark Gray
    onSurfaceVariant = Color(0xFFCAC4D0), // Light Gray
    outline = Color(0xFF938F99), // Grayish Purple
    outlineVariant = Color(0xFF49454F), // Dark Gray
    inverseSurface = Color(0xFFE6E1E5), // Light Gray
    inverseOnSurface = Color(0xFF1C1B1F), // Near Black
    inversePrimary = Color(0xFF0E5B4A), // Deep Green
    scrim = Color(0xFF000000), // Black
    surfaceTint = Color(0xFFA8D5C2), // Light Green
    surfaceContainerLowest = Color(0xFF0F0F12), // Near Black
    surfaceContainerLow = Color(0xFF1A191D), // Dark Gray
    surfaceContainer = Color(0xFF1E1D21), // Dark Gray
    surfaceContainerHigh = Color(0xFF282729), // Dark Gray
    surfaceContainerHighest = Color(0xFF333234), // Dark Gray
)

// Semantic Colors
val colorPing = Color(0xFF0E7A5F) // Deep Green
val colorPingRed = Color(0xFFFF0099) // Pink Red
val colorConfigType = Color(0xFF0E5B4A) // Deep Green
val colorFabActive = Color(0xFF0E5B4A) // Deep Green
val colorFabInactiveLight = Color(0xFF9C9C9C) // Gray
val colorFabInactiveDark = Color(0xFF646464) // Dark Gray
val dividerColorLight = Color(0xFFD6E0D9) // Light Gray-Green
val dividerColorDark = Color(0xFF424242) // Dark Gray

// Toast Colors 85%
val toastNormalBgLight = Color(0xD9353A3E) // Dark Gray
val toastNormalBgDark = Color(0xD94A4F54) // Darker Gray
val toastSuccessBg = Color(0xD9388E3C) // Green
val toastErrorBg = Color(0xD9D50000) // Red
val toastInfoBg = Color(0xD93F51B5) // Indigo Blue
val toastIconCircleBg = Color(0x33FFFFFF) // Semi-transparent White
val toastTextColor = Color.White // White

object ThemeManager {
    private val _themeMode = MutableStateFlow(
        MmkvManager.decodeSettingsString(AppConfig.PREF_UI_MODE_NIGHT, "0") ?: "0"
    )
    val themeMode: StateFlow<String> = _themeMode.asStateFlow()

    private val _dynamicColorEnabled = MutableStateFlow(
        // Bettbox-style palette is the default: dynamic color would replace it
        // with the wallpaper palette on Android S+, so fresh installs default
        // to off. Users can still re-enable it in Settings.
        MmkvManager.decodeSettingsBool(AppConfig.PREF_DYNAMIC_COLOR, false)
    )
    val dynamicColorEnabled: StateFlow<Boolean> = _dynamicColorEnabled.asStateFlow()

    fun setThemeMode(mode: String) {
        MmkvManager.encodeSettings(AppConfig.PREF_UI_MODE_NIGHT, mode)
        _themeMode.value = mode
    }

    fun setDynamicColorEnabled(enabled: Boolean) {
        MmkvManager.encodeSettings(AppConfig.PREF_DYNAMIC_COLOR, enabled)
        _dynamicColorEnabled.value = enabled
    }

    fun refresh() {
        _themeMode.value =
            MmkvManager.decodeSettingsString(AppConfig.PREF_UI_MODE_NIGHT, "0") ?: "0"
        _dynamicColorEnabled.value =
            MmkvManager.decodeSettingsBool(AppConfig.PREF_DYNAMIC_COLOR, false)
    }
}

@Composable
fun resolveDarkTheme(): Boolean {
    val mode by ThemeManager.themeMode.collectAsState()
    return when (mode) {
        "1" -> false
        "2" -> true
        else -> isSystemInDarkTheme()
    }
}

val LocalDarkTheme = compositionLocalOf { false }

@Composable
fun AppTheme(
    darkTheme: Boolean = resolveDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicColor by ThemeManager.dynamicColorEnabled.collectAsState()
    val context = LocalContext.current
    val colorScheme = when {
        // Dynamic color takes precedence when the user enables it in
        // Settings; turn it off there to see the Bettbox-style palette.
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColor
        else -> LightColor
    }
    val snackbarController = rememberAppSnackbarController()

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as? Activity ?: return@SideEffect
            val window = activity.window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalAppSnackbar provides snackbarController
    ) {
        MaterialTheme(
            colorScheme = colorScheme
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AppSnackbarBridge(controller = snackbarController)
                content()
                AppSnackbarHost(hostState = snackbarController.hostState)
            }
        }
    }
}
