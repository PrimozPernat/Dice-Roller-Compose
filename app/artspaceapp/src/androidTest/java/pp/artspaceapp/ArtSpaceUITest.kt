package pp.artspaceapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pp.artspaceapp.ui.theme.ArtSpaceComposeTheme

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ArtSpaceUITest {

    @get:Rule
    var rule = createComposeRule()
    @Test
    fun check_if_setup_ready() {
        rule.setContent {
            ArtSpaceComposeTheme {
                ArtSpace(list = generateArtistList())
            }
        }

        rule.onNodeWithText("Title 1").assertExists("No first node")
    }

    @Test
    fun check_if_next_and_previous_work_correctly() {
        rule.setContent {
            ArtSpaceComposeTheme {
                ArtSpace(list = generateArtistList(10))
            }
        }

        rule.onNodeWithText("Previous").performClick()
        rule.onNodeWithText("Title 10").assertExists("No 10 item")
        rule.onNodeWithText("Previous").performClick()
        rule.onNodeWithText("Title 9").assertExists("No 9 item")

        // Two times click next, to go back to first
        rule.onNodeWithText("Next").performClick()
        rule.onNodeWithText("Next").performClick()
        rule.onNodeWithText("Title 2").assertExists("No 1 item")
    }
}