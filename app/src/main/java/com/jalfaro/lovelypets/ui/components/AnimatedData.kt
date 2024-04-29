package com.jalfaro.lovelypets.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jalfaro.lovelypets.R
import com.jalfaro.lovelypets.models.Pet
import kotlinx.coroutines.delay

@Composable
fun AnimatedData(
   showDelete : Boolean,
   pet: Pet,
   deletePet: (Pet) -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = "animation") {
        delay(500)
        isLoading = false
    }

    val targetSize = if (isLoading) 0.dp else 200.dp

    val animatedSize by animateDpAsState(
        targetValue = targetSize,
        animationSpec = tween(durationMillis = 2000)
    )

    Column(
        modifier= Modifier
                .padding(16.dp, 8.dp)
                .height(animatedSize)
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = pet.name,
                fontSize = 25.sp,
            )
        }
        if (showDelete) {
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.align(Alignment.End)) {
                IconButton(
                    onClick = { deletePet(pet) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(id = R.string.nav_back_content_description),
                    )
                }
            }
        }
    }
}