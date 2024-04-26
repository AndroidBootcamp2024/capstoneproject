package com.jalfaro.lovelypets.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jalfaro.lovelypets.R

@Composable
fun MenuButton(
    drawable: Int,
    text: String,
    modifier: Modifier = Modifier,
    action: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
           .fillMaxWidth()
            .padding(16.dp)
            .clickable ( onClick = action ),
    ) {
        Column(
          modifier = Modifier
              .padding(8.dp)
              .fillMaxWidth()
              .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = text,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(start = 4.dp, top= 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }

}

@Composable
@Preview
fun MenuButtonPreview() {
    MenuButton(
        action ={},
        drawable = R.drawable.ic_launcher_background,
        text = "Test",
    )
}