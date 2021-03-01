package com.example.petadoption

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petadoption.ui.theme.PetAdoptionTheme


@Composable
fun PetCard(puppy: Puppy, modifier: Modifier = Modifier) {
    Card(modifier, shape = RoundedCornerShape(20.dp),elevation = 2.dp) {
        Column() {
            Box {
                Image(
                    painterResource(puppy.resId),
                    null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Column {
                    Text(text = puppy.name, fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp))
                    Text(text = puppy.gender, fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 20.dp, start = 20.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun DogPreview() {
    PetAdoptionTheme {
        PetCard(puppy = PetsDetails.puppies.first())
    }
}
