package pp.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.IntegerRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pp.artspaceapp.ui.theme.ArtSpaceComposeTheme

class ArtSpaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace(generateArtistList())
                }
            }
        }
    }
}

@Composable
fun ArtSpace(list: MutableList<ArtList>, modifier: Modifier = Modifier) {

    var currentArtPosition by remember {
        mutableIntStateOf(0)
    }

    val currentArt = list[currentArtPosition]

    Column(modifier = modifier
        .fillMaxSize()) {
        ImageArt(imageId = 2, modifier)
        Spacer(
            modifier = modifier
                .padding(top = 16.dp)
                .weight(1f)
        )
        ArtworkTitle(title = currentArt.title, subtitle = currentArt.subtitle, modifier)
        Spacer(modifier = modifier.padding(top = 16.dp))
        ButtonNavigation(
            onPrevious = {
                currentArtPosition = nextArtPosition(currentArtPosition - 1, list.size - 1)
            },
            onNext = {
                currentArtPosition = nextArtPosition(currentArtPosition + 1, list.size - 1)

            },
            modifier
        )
    }
}

@VisibleForTesting
fun nextArtPosition(nextPosition: Int, maxPosition: Int): Int {
    return if (nextPosition > maxPosition) {
        0
    } else if (nextPosition < 0) {
        maxPosition
    } else {
        nextPosition
    }
}

@Composable
fun ButtonNavigation(onPrevious: () -> Unit, onNext: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Button(onClick = {
            onPrevious.invoke()
        }) {
            Text(text = stringResource(id = R.string.previous))
        }
        Button(onClick = {
            onNext.invoke()
        }) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Composable
fun ImageArt(imageId: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .padding(horizontal = 16.dp)
            .shadow(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(id = R.string.image_description),
        )
    }
}

@Composable
fun ArtworkTitle(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(colorResource(id = R.color.teal_200))
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(text = subtitle, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Monospace)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceComposeTheme {
        ArtSpace(generateArtistList())
    }
}

data class ArtList(
    val title: String,
    val subtitle: String,
    @IntegerRes val imageId: Int,
)

fun generateArtistList(maxItems: Int = 10): MutableList<ArtList> {
    val list = mutableListOf<ArtList>()
    for (i in 1..maxItems) {
        list.add(ArtList("Title $i", "Subtitle $i", i))
    }
    return list
}