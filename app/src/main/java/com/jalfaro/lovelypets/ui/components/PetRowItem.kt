package com.jalfaro.lovelypets.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.jalfaro.lovelypets.R
import com.jalfaro.lovelypets.models.Pet

@Composable
fun PetRowItem(
    showDelete: Boolean,
    pet: Pet,
    deletePet: (pet:Pet) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row() {
            Image(
                painter = rememberImagePainter(pet.picture),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )

            AnimatedData(
                showDelete = showDelete,
                pet=pet,
                deletePet= deletePet
            )
        }
    }
}

@Composable
@Preview
fun PetRowItemPreview() {
    PetRowItem(
        pet = Pet(1, "Pet", "Dog", ""),
        deletePet = { },
        showDelete = false
        )
}