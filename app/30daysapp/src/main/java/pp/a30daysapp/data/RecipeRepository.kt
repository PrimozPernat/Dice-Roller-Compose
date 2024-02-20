package pp.a30daysapp.data

import androidx.compose.runtime.mutableStateListOf
import pp.a30daysapp.R
import java.util.Random
import java.util.UUID

object RecipeGenerator {
    fun generate(): MutableList<Recipe> {
        val list = arrayListOf(
            Recipe(
                R.string.scrambled_eggs_title,
                R.string.scrambled_eggs_instructions,
                Duration.FIVE
            ),
            Recipe(
                R.string.grill_sandwich_title,
                R.string.grill_sandwich_instructions,
                Duration.TEN
            ),
            Recipe(
                R.string.spaghetti_aglio_e_olio_title,
                R.string.spaghetti_aglio_e_olio_instructions,
                Duration.FIFTEEN
            )
        )

        val generatedList = mutableStateListOf<Recipe>()

        for (i in 1..30) {
            generatedList.add(
                list[(0..<list.size).random()].copy(
                    id = UUID.randomUUID().toString() + "$i pp"
                )
            )
        }

        return generatedList
    }
}