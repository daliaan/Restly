package dalian.razvan.cucer.core.data.network.model.response

import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(@SerializedName("success") val isSuccessful: Boolean,
                               @SerializedName("statusCode") val statusCode: Int = 0,
                               @SerializedName("message") val message: String? = null,
                                val restaurantsResponseData: RestaurantsResponseData)
