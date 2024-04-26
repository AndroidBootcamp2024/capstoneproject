package com.jalfaro.lovelypets.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jalfaro.lovelypets.R

import com.jalfaro.lovelypets.ui.components.MenuButton

@Composable
fun MenuScreen(
    onClickRegisterAPet: () -> Unit,
    onClickViewAllPets : () -> Unit,
    onClickSettings :() -> Unit,
    onClickAbout: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.menu_title),
                        fontSize = 25.sp,
                        style = MaterialTheme.typography.bodyMedium
                ) },
                actions = {
                    IconButton(
                        onClick = onClickSettings,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(id = R.string.setting_message),
                        )
                    }
                    IconButton(
                        onClick = onClickAbout,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Help,
                            contentDescription = stringResource(id = R.string.about_message),
                        )
                    }
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(padding)
                ) {
                    MenuButton(
                        action = onClickRegisterAPet,
                        drawable = R.drawable.pet_register,
                        text = stringResource(R.string.option_add_a_pet),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                    MenuButton(
                        action = onClickViewAllPets,
                        drawable = R.drawable.pet_list,
                        text = stringResource(R.string.option_view_a_pet),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )

                }
            }
        })
}

@Composable
@Preview
fun PreviewMenuScreen() {
    MenuScreen(
        onClickRegisterAPet = {},
        onClickViewAllPets = {},
        onClickSettings = {},
        onClickAbout = {}
    )
}