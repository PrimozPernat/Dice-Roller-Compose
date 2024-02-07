package pp.dice.roller.lemodaned

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pp.dice.roller.lemodaned.ui.theme.DiceRollerComposeTheme

class LemonadeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerComposeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableIntStateOf(1) }

    var squeezeStep by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        when (currentStep) {
            1 -> {
                LemonadeMakerCompose(
                    textId = R.string.tap_on_lemon_tree,
                    imageId = R.drawable.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeStep = (2..4).random()
                    }
                )
            }

            2 -> {
                LemonadeMakerCompose(
                    textId = R.string.keep_tapping_the_lemon,
                    imageId = R.drawable.lemon_squeeze,
                    onImageClick = {
                        squeezeStep--
                        if (squeezeStep == 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                LemonadeMakerCompose(
                    textId = R.string.tap_the_lemonade_to_drink,
                    imageId = R.drawable.lemon_drink,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }

            4 -> {
                LemonadeMakerCompose(
                    textId = R.string.tap_the_empty_glass,
                    imageId = R.drawable.lemon_restart,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }


}

@Composable
fun LemonadeMakerCompose(
    textId: Int,
    imageId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(
                id = textId
            ),
            Modifier
                .clickable {
                    onImageClick.invoke()
                }
                .background(
                    color = colorResource(id = R.color.teal_200),
                    shape = RoundedCornerShape(32.dp)
                )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = textId),
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
    }
}
