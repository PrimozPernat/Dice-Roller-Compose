package pp.dice.roller.listapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringId: Int,
    val score: Int,
    @DrawableRes val imageIdRes: Int
)