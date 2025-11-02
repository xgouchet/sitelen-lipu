package fr.xgouchet.sitelenlipu.ui.atom

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import fr.xgouchet.sitelenlipu.ui.model.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    selectedScreen: Screen,
    onSelectScreen: (Screen) -> Unit
) {
    NavigationBar {
        Screen.entries.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = screen == selectedScreen,
                label = {
                    Text(screen.label)
                },
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = screen.label
                    )
                },
                onClick = {
                    onSelectScreen(screen)
                    // TODO
//                            navigationSelectedItem = index
//                            navController.navigate(navigationItem.route) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
                }
            )
        }
    }
}