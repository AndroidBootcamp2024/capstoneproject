package com.jalfaro.lovelypets.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsToggleRow(
    label: String,
    isToggleChecked: Boolean,
    onToggleChanged: (Boolean) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
        )
        Switch(
            checked = isToggleChecked,
            onCheckedChange = onToggleChanged,
        )
    }
}

@Preview(widthDp = 320)
@Composable
fun SettingsToggleRowPreview() {
    SettingsToggleRow(
        label = "Dark Mode",
        isToggleChecked = true,
        onToggleChanged = {},
    )
}