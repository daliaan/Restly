package dalian.razvan.cucer.core.data.network.model.request

import com.google.gson.annotations.SerializedName

data class RestaurantsRequest(@SerializedName("Page") val pageNumber: Int,
                              @SerializedName("PageSize") val pageSize: Int,
                              @SerializedName("RestaurantCategoryIdList") val categoryIds: ArrayList<Int> = arrayListOf(),
                              @SerializedName("Term") val queryTerm: String = "")