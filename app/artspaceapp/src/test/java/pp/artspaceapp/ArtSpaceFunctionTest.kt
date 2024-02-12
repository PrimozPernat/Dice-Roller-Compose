package pp.artspaceapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ArtSpaceFunctionTest {
    @Test
    fun next_value_bigger_than_max_starts_from_beginning() {
        val position = nextArtPosition(5, 4)
        assertEquals(position, 0)
    }

    @Test
    fun next_value_smaller_than_zero_goes_to_max() {
        val position = nextArtPosition(-1, 4)
        assertEquals(position, 4)
    }

    @Test
    fun next_value_should_be_the_same_as_input() {
        val input = 3
        val max = 4
        val position = nextArtPosition(input, max)
        assertEquals(position, input)
    }
}