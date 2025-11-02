package fr.xgouchet.sitelenlipu.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import fr.xgouchet.sitelenlipu.data.viewmodel.FlashCardViewModel
import fr.xgouchet.sitelenlipu.ui.atom.BottomNavBar
import fr.xgouchet.sitelenlipu.ui.model.Screen

@Composable
fun MainScreen(
    flashCardViewModel: FlashCardViewModel
) {

    var selectedScreen: Screen by remember { mutableStateOf(Screen.FLASHCARD) }

    Scaffold(
        bottomBar = {
            BottomNavBar(selectedScreen) { selectedScreen = it }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (selectedScreen == Screen.FLASHCARD) {
            FlashCardScreen(
                flashCardViewModel,
                Modifier.padding(innerPadding)
            )
        }
    }
}