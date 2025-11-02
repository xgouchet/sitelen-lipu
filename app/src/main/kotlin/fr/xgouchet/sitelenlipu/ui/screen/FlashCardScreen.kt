package fr.xgouchet.sitelenlipu.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.xgouchet.sitelenlipu.data.model.CardDisplay
import fr.xgouchet.sitelenlipu.data.model.WordInfo
import fr.xgouchet.sitelenlipu.data.viewmodel.FlashCardViewModel
import fr.xgouchet.sitelenlipu.ui.atom.FlashCard
import fr.xgouchet.sitelenlipu.ui.theme.DarkGreen
import fr.xgouchet.sitelenlipu.ui.theme.DarkRed

@Composable
fun FlashCardScreen(
    viewModel: FlashCardViewModel,
    modifier: Modifier = Modifier
) {
    val wordInfo by viewModel.wordInfo.observeAsState()
    var secretCardDisplay by remember { mutableStateOf(CardDisplay.VIEW_TOKI_AND_SITELEN) }
    var showSecret by remember { mutableStateOf(true) }

    val currentWordInfo = wordInfo
    val cardState = if (showSecret) secretCardDisplay else CardDisplay.VIEW_ALL

    Scaffold(
        bottomBar = {
            BottomToolBar(
                secretCardDisplay,
                onUpdateSecretCardDisplay = { secretCardDisplay = it },
                onRefreshCard = {
                    showSecret = true
                    viewModel.fetchRandomWord()
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            if (currentWordInfo != null) {
                FlashCard(
                    wordInfo = currentWordInfo,
                    cardDisplay = cardState,
                    modifier = Modifier.padding(16.dp)
                ) {
                    showSecret = !showSecret
                }
            } else {

            }
        }
    }
}

@Composable
private fun BottomToolBar(
    display: CardDisplay,
    onUpdateSecretCardDisplay: (CardDisplay) -> Unit,
    onRefreshCard: () -> Unit
) {
    BottomAppBar(
        actions = {
            IconButton(
                onClick = {
                    onUpdateSecretCardDisplay(
                        display.copy(sitelenPona = !display.sitelenPona)
                    )
                }
            ) {
                Text(
                    text = "sitelen pona",
                    color = if (display.sitelenPona) DarkGreen else DarkRed,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            IconButton(
                onClick = {
                    onUpdateSecretCardDisplay(
                        display.copy(sitelenPilin = !display.sitelenPilin)
                    )
                }
            ) {
                Text(
                    text = "sitelen pilin",
                    color = if (display.sitelenPilin) DarkGreen else DarkRed,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            IconButton(
                onClick = {
                    onUpdateSecretCardDisplay(
                        display.copy(sitelenJelo = !display.sitelenJelo)
                    )
                }
            ) {
                Text(
                    text = "sitelen jelo",
                    color = if (display.sitelenJelo) DarkGreen else DarkRed,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            IconButton(
                onClick = {
                    onUpdateSecretCardDisplay(
                        display.copy(tokiPona = !display.tokiPona)
                    )
                }
            ) {
                Text(
                    text = "toki pona",
                    color = if (display.tokiPona) DarkGreen else DarkRed,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            IconButton(
                onClick = {
                    onUpdateSecretCardDisplay(
                        display.copy(tokiJan = !display.tokiJan)
                    )
                }
            ) {
                Text(
                    text = "toki jan",
                    color = if (display.tokiJan) DarkGreen else DarkRed,
                    style = MaterialTheme.typography.displaySmall
                )
            }
        },
        floatingActionButton = {
            RefreshButton(
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                onRefresh = onRefreshCard
            )
        }
    )
}

@Composable
fun RefreshButton(
    containerColor: Color = MaterialTheme.colorScheme.primary,
    onRefresh: () -> Unit
) {
    FloatingActionButton(
        onClick = onRefresh,
        containerColor = containerColor,
        contentColor = MaterialTheme.colorScheme.surfaceBright,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
    ) {
        Icon(Icons.Filled.Refresh, "Refresh card")
    }
}