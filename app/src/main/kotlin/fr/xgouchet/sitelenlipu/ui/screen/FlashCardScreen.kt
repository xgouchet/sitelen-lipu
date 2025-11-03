package fr.xgouchet.sitelenlipu.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun FlashCardScreen(
    viewModel: FlashCardViewModel,
    modifier: Modifier = Modifier
) {
    val wordInfo by viewModel.wordInfo.observeAsState()
    val secretDisplay by viewModel.secretDisplay.observeAsState()
    val fullDisplay by viewModel.fullDisplay.observeAsState()
    var showSecret by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.refreshDisplayStates()
    }

    val currentWordInfo = wordInfo
    val cardState = if (showSecret) secretDisplay else fullDisplay

    Scaffold(
        floatingActionButton = {
            RefreshButton {
                showSecret = true
                viewModel.fetchRandomWord()
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            FlashCard(
                wordInfo = currentWordInfo ?: WordInfo.TOKI_PONA,
                cardDisplay = cardState ?: CardDisplay.VIEW_ALL,
                modifier = Modifier.padding(16.dp)
            ) {
                showSecret = !showSecret
            }
        }
    }
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