package fr.xgouchet.sitelenlipu.ui.atom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.xgouchet.sitelenlipu.data.model.CardDisplay
import fr.xgouchet.sitelenlipu.data.model.WordInfo
import fr.xgouchet.sitelenlipu.ui.theme.SitelenLipuTheme


const val NBSP = Typography.nbsp.toString()

@Composable
fun FlashCard(
    wordInfo: WordInfo,
    cardDisplay: CardDisplay,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = onClick,
        modifier = modifier
    ) {
        Column(Modifier.fillMaxWidth().padding(8.dp)) {
            Text(
                text= wordInfo.usageCategory,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start).padding(8.dp),
            )
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = if (cardDisplay.sitelenPona) wordInfo.id else NBSP,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp),
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = if (cardDisplay.sitelenPilin) wordInfo.representations.get("sitelen_emosi").orEmpty() else NBSP,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Text(
                text = if (cardDisplay.tokiPona) wordInfo.id else NBSP,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = if (cardDisplay.tokiInli) wordInfo.puVerbatim["en"].orEmpty() else NBSP,
                textAlign = TextAlign.Start,
                maxLines = 10,
                modifier = Modifier.align(Alignment.Start).padding(4.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = if (cardDisplay.tokiKanse) wordInfo.puVerbatim["fr"].orEmpty() else NBSP,
                textAlign = TextAlign.Start,
                maxLines = 10,
                modifier = Modifier.align(Alignment.Start).padding(4.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview
fun PreviewCard() {
    SitelenLipuTheme {
        FlashCard(
            WordInfo(
                id = "kepeken",
                seeAlso = emptyList(),
                usageCategory = "core",
                puVerbatim = mapOf(
                    "fr" to "PRÉPOSITION en utilisant, avec, au moyen de",
                    "en" to "PREPOSITION to use, with, by means of",
                ),
                representations = mapOf(
                    "sitelen_emosi" to "\uD83D\uDD27"
                ),
                deprecated = false
            ),
            CardDisplay.VIEW_ALL
        )
    }
}