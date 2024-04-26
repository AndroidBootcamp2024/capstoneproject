package com.jalfaro.lovelypets.ui.screens.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.jalfaro.lovelypets.R
import com.jalfaro.lovelypets.ui.components.SettingsToggleRow

@Composable
fun SettingScreen(
    viewModel: SettingViewModel,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
    topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.setting_title),
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.bodyMedium
                ) },
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.nav_back_content_description),
                    )
                }
            }
        )
    },
    content = { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            SettingsToggleRow(
                label = stringResource(id = R.string.dark_mode),
                isToggleChecked = uiState.darkMode,
                onToggleChanged = { viewModel.toggleDarkMode() },
            )
            SettingsToggleRow(
                label = stringResource(id = R.string.allow_delete),
                isToggleChecked = uiState.deleteEnabled,
                onToggleChanged = { viewModel.toggleAllowDelete() },
            )
            SettingsToggleRow(
                label = stringResource(id = R.string.allow_rotate),
                isToggleChecked = uiState.rotationEnabled,
                onToggleChanged = { viewModel.toggleRotation() },
            )
        }
    })
}
