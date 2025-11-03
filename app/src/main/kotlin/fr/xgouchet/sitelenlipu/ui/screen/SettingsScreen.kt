package fr.xgouchet.sitelenlipu.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.checkboxPreference
import me.zhanghai.compose.preference.preferenceCategory

@Composable
fun SettingsScreen(
    modifier: Modifier
) {
    ProvidePreferenceLocals {
        LazyColumn(modifier = modifier) {
            preferenceCategory(
                key = "secret-display",
                title = {
                    Text(
                        text = "Secret Display",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
            checkboxPreference(
                key = "secret-display:sitelen-pona",
                defaultValue = true,
                title = {
                    Text(
                        text = "sitelen pona",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "sitelen pona",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )
            checkboxPreference(
                key = "secret-display:sitelen-pilin",
                defaultValue = true,
                title = {
                    Text(
                        text = "sitelen pilin",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "sitelen pilin",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )
            checkboxPreference(
                key = "secret-display:sitelen-jelo",
                defaultValue = true,
                title = {
                    Text(
                        text = "sitelen jelo",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "sitelen jelo",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )

            checkboxPreference(
                key = "secret-display:toki-pona",
                defaultValue = false,
                title = {
                    Text(
                        text = "toki pona",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "toki pona",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )
            checkboxPreference(
                key = "secret-display:toki-jan",
                defaultValue = false,
                title = {
                    Text(
                        text = "toki jan",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "toki jan",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )

            preferenceCategory(
                key = "full-display",
                title = {
                    Text(
                        text = "Full Display",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
            checkboxPreference(
                key = "full-display:sitelen-pona",
                defaultValue = true,
                title = {
                    Text(
                        text = "sitelen pona",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "sitelen pona",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )
            checkboxPreference(
                key = "full-display:sitelen-pilin",
                defaultValue = true,
                title = {
                    Text(
                        text = "sitelen pilin",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "sitelen pilin",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )
            checkboxPreference(
                key = "full-display:sitelen-jelo",
                defaultValue = true,
                title = {
                    Text(
                        text = "sitelen jelo",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "sitelen jelo",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )

            checkboxPreference(
                key = "full-display:toki-pona",
                defaultValue = true,
                title = {
                    Text(
                        text = "toki pona",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "toki pona",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )
            checkboxPreference(
                key = "full-display:toki-jan",
                defaultValue = true,
                title = {
                    Text(
                        text = "toki jan",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "toki jan",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
            )
        }
    }
}