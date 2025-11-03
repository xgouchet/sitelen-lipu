package fr.xgouchet.sitelenlipu.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Square
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    FLASHCARD("flashcard", "sitelen lipu", Icons.Outlined.Square),
    WORD_LIST("wordlist", "nimi ale", Icons.AutoMirrored.Outlined.List),
    SETTINGS("settings", "ante e pilin", Icons.Outlined.Settings)
}