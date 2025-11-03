package fr.xgouchet.sitelenlipu.ui.atom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.xgouchet.sitelenlipu.data.model.CardDisplay
import fr.xgouchet.sitelenlipu.data.model.WordInfo
import fr.xgouchet.sitelenlipu.ui.theme.CommonAccent
import fr.xgouchet.sitelenlipu.ui.theme.CoreAccent
import fr.xgouchet.sitelenlipu.ui.theme.ObscureAccent
import fr.xgouchet.sitelenlipu.ui.theme.SitelenLipuTheme
import fr.xgouchet.sitelenlipu.ui.theme.UncommonAccent


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
        colors = CardDefaults.cardColors(
            containerColor = CategoryColor(wordInfo)
        ),
        onClick = onClick,
        modifier = modifier
    ) {
        Column(Modifier.fillMaxWidth().padding(8.dp)) {
            Text(
                text = wordInfo.usageCategory,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start).padding(8.dp),
            )
            Row(Modifier.align(Alignment.CenterHorizontally)) {
                if (cardDisplay.sitelenPona) {
                    Text(
                        text = wordInfo.tokiPona(),
                        modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp),
                        style = MaterialTheme.typography.displayLarge
                    )
                }
                if (cardDisplay.sitelenPilin) {
                    Text(
                        text = wordInfo.sitelenPilin(),
                        modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                if (cardDisplay.sitelenJelo) {
                    Text(
                        text = wordInfo.sitelenJelo(),
                        modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            if (cardDisplay.tokiPona) {
                Text(
                    text =  wordInfo.tokiPona() ,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            if (cardDisplay.tokiJan) {
                wordInfo.tokiJan().forEach { (key, value) ->
                    Row {
                        Text(
                            text = key,
                            modifier = Modifier.align(Alignment.Top).padding(8.dp).width(48.dp),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = value,
                            modifier = Modifier.align(Alignment.Top).padding(8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
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
                usageCategory = "cofre",
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