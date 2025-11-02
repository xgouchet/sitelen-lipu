package fr.xgouchet.sitelenlipu.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Square
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    FLASHCARD ("flashcard", "FlashCard", Icons.Outlined.Square),
    DICTIONARY_TOKI_INLI("toki pona tawa toki Inli", "toki pona-> Inli", Icons.AutoMirrored.Outlined.ArrowForward),
    DICTIONARY_INLI_TOKI("toki Inli tawa toki pona", "toki pona-> Inli", Icons.AutoMirrored.Outlined.ArrowBack)
}