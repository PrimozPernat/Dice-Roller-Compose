package pp.a30daysapp.data

import androidx.annotation.StringRes
import pp.a30daysapp.R
import java.util.UUID

data class Recipe(
    @StringRes val title: Int,
    @StringRes val instruction: Int,
    val duration: Duration,
    val id: String = UUID.randomUUID().toString(),
)

enum class Duration(@StringRes val duration: Int) {
    FIVE(R.string.five_min_duration), TEN(R.string.ten_min_duration), FIFTEEN(R.string.fifteen_min_duration)
}