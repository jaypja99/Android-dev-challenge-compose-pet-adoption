
package com.example.petadoption


import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petadoption.ui.theme.PetAdoptionTheme

private const val START_TOP_PADDING = 290

@Composable
fun PetDetailsCard(puppy: Puppy, modifier: Modifier = Modifier) {
    val context = LocalContext.current as? Activity
    val scrollState = rememberScrollState()


    val imageOffset = (-scrollState.value * 0.2f).dp
    val iconBackgroundAlpha = ((scrollState.value / START_TOP_PADDING.toFloat()) * 0.2f).coerceAtMost(0.2f)

    Box {
        Image(
            painter = painterResource(id = puppy.resId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .offset(y = imageOffset)
                .fillMaxWidth()
                .height(250.dp)

        )

        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(top = 230.dp, end= 20.dp, start= 20.dp)
                .background(
                    MaterialTheme.colors.surface,
                    RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(all = 32.dp)
        ) {
            Text(text = puppy.name, style = MaterialTheme.typography.h4)
            Text(text = puppy.gender)
            Spacer(Modifier.size(16.dp))
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = puppy.description)
        }

        IconButton(onClick = { context?.onBackPressed() }, modifier = Modifier.padding(8.dp).background(Color.Black.copy(alpha = iconBackgroundAlpha), shape = CircleShape)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back_24),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp),
                tint = Color.White
            )
        }
    }
}


@Preview
@Composable
private fun DogPreview() {
    PetAdoptionTheme {
        PetDetailsCard(puppy = PetsDetails.puppies.first())
    }
}
