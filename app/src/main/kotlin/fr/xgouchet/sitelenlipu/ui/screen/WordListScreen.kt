package fr.xgouchet.sitelenlipu.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import fr.xgouchet.sitelenlipu.data.model.WordId
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun WordListScreen(
    modifier: Modifier = Modifier,
    onSelectWord: (WordId) -> Unit
) {
    val listState = rememberLazyListState()
    val items = remember { WordId.entries }
    val headers = remember { items.map { it.name.first() }.toSet().toList() }

    val offsets = remember { mutableStateMapOf<Int, Float>() }
    var selectedHeaderIndex by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    fun updateSelectedIndexIfNeeded(offset: Float) {
        val index = offsets
            .mapValues { abs(it.value - offset) }
            .entries
            .minByOrNull { it.value }
            ?.key ?: return
        if (selectedHeaderIndex == index) return
        selectedHeaderIndex = index
        val selectedItemIndex =
            items.indexOfFirst { it.name.first() == headers[selectedHeaderIndex] }
        scope.launch {
            listState.scrollToItem(selectedItemIndex)
        }
    }

    Row(modifier) {
        LazyColumn(
            Modifier.weight(1f),
            state = listState,
        ) {
            items(items, key = { it.name }) { wordId ->
                Column(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onSelectWord(wordId) }
                ) {
                    Row(Modifier.padding(8.dp)) {
                        Text(
                            text = wordId.name.lowercase(),
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.align(Alignment.Bottom)
                                .padding(start = 8.dp, end = 8.dp)
                        )
                        Text(
                            text = wordId.name.lowercase(),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.Bottom)
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .pointerInput(Unit) {
                    detectTapGestures {
                        updateSelectedIndexIfNeeded(it.y)
                    }
                }
                .pointerInput(Unit) {
                    detectVerticalDragGestures { change, _ ->
                        updateSelectedIndexIfNeeded(change.position.y)
                    }
                }
        ) {
            headers.forEachIndexed { i, header ->
                Text(
                    "$header",
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .onGloballyPositioned {
                            offsets[i] = it.boundsInParent().center.y
                        }
                )
            }
        }
    }
}
