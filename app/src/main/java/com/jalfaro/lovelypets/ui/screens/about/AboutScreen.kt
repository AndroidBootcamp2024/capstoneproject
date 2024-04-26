package com.jalfaro.lovelypets.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jalfaro.lovelypets.R

@Composable
fun AboutScreen(
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.about_title),
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
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top=padding.calculateTopPadding(), start=16.dp, end=16.dp)
                    .fillMaxHeight()

            ) {
                Image(
                    painter = painterResource(R.drawable.kodeco_logo),
                    contentDescription = stringResource(R.string.kodeco),
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 60.dp)
                )
                Text(
                    text= stringResource(id = R.string.about_me_1),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier=Modifier.fillMaxWidth()
                )
                Text(
                    text= stringResource(id = R.string.about_me_2),
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier=Modifier.fillMaxWidth()
                )
                Text(
                    text= stringResource(id = R.string.about_me_3),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier=Modifier.fillMaxWidth()
                )
            }
        })
}

@Composable
@Preview
fun AboutScreenPreview() {
    AboutScreen(
        onNavigateUp = {}
    )
}