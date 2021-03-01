package com.example.petadoption

import android.app.Activity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petadoption.ui.theme.PetAdoptionTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetAdoptionTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyApp() {
    var selectedPuppy by remember { mutableStateOf<Puppy?>(null) }

    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (selectedPuppy != null) {
                    selectedPuppy = null
                } else {
                    (context as? Activity)?.finish()
                }
            }
        }
    }

    DisposableEffect(key1 = backDispatcher) {
        backDispatcher.onBackPressedDispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }

    Surface(color = MaterialTheme.colors.background) {
        selectedPuppy?.let { puppy ->
            PetDetailsCard(puppy = puppy)
        } ?: run {
            PuppyGrid {
                selectedPuppy = it
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PuppyGrid(onPuppyClick: (Puppy) -> Unit) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(Modifier.padding(16.dp)) {
            Text("Hey 🖐 User",fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text("Let us be a friend",fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.size(16.dp))
        StaggeredVerticalGrid(maxColumnWidth = 300.dp) {
            PetsDetails.puppies.forEach {
                Box(Modifier.padding(8.dp)) {
                    PetCard(
                        puppy = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPuppyClick(it) }
                    )
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    PetAdoptionTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    PetAdoptionTheme(darkTheme = true) {
        MyApp()
    }
}
