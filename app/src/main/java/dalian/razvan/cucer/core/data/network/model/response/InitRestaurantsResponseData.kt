package dalian.razvan.cucer.core.data.network.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class InitRestaurantsResponseData(@SerializedName("restaurantFirstPage") val restaurantsResponseData: RestaurantsResponseData,
                                       @SerializedName("restaurantsCategories") val restaurantCategories: ArrayList<Locale.Category> = arrayListOf())
