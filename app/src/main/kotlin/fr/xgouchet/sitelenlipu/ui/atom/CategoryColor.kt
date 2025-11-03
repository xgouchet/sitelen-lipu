package fr.xgouchet.sitelenlipu.ui.atom

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fr.xgouchet.sitelenlipu.data.model.WordInfo
import fr.xgouchet.sitelenlipu.ui.theme.CommonAccent
import fr.xgouchet.sitelenlipu.ui.theme.CoreAccent
import fr.xgouchet.sitelenlipu.ui.theme.ObscureAccent
import fr.xgouchet.sitelenlipu.ui.theme.UncommonAccent


@Composable
fun CategoryColor(wordInfo: WordInfo): Color {
    return when (wordInfo.usageCategory) {
        "core" -> CoreAccent
        "common" -> CommonAccent
        "uncommon" -> UncommonAccent
        "obscure" -> ObscureAccent
        else -> MaterialTheme.colorScheme.errorContainer
    }
}