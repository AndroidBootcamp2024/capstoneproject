package com.jalfaro.lovelypets.ui.screens.display

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jalfaro.lovelypets.R
import com.jalfaro.lovelypets.ui.components.PetRowItem


@Composable
fun DisplayScreen(
    viewModel: DisplayViewModel,
    onNavigateUp: () -> Unit,
    showDelete: Boolean
) {
    val pets by viewModel.pets.collectAsState()
    LaunchedEffect(key1 = "loadpets") {
        viewModel.fetchPets()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.display_title),
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
            if (pets.size > 0) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                ) {
                    itemsIndexed(pets) { index, pet ->
                        PetRowItem(
                            showDelete = showDelete,
                            pet = pet,
                            deletePet = {
                                viewModel.deletePet(it)
                            }
                        )
                    }
                }
            } else {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(padding)) {
                    Text(text= stringResource(id = R.string.no_pets), modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
    )
}

@Composable
@Preview
fun DisplayScreenPreview() {
    DisplayScreen(
        viewModel = hiltViewModel(),
        onNavigateUp = {},
        showDelete = false
    )
}


