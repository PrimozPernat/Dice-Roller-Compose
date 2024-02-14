package pp.dogapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    val image: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)
