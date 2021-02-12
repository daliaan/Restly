package dalian.razvan.cucer.core.data.network.model.response

import android.os.Parcelable
import java.util.*
import kotlin.collections.ArrayList

data class InitRestaurantsResponseData(val restaurantsResponseData: RestaurantsResponseData,
                                       val restaurantCategories: ArrayList<Locale.Category> = arrayListOf())
