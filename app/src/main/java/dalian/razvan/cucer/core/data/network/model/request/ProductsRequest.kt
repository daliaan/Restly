package dalian.razvan.cucer.core.data.network.model.request

import com.google.gson.annotations.SerializedName

data class ProductsRequest(@SerializedName("Page") val page: Int = 0,
                           @SerializedName("PageSize") val pageSize: Int = 0,
                           @SerializedName("RestaurantId") val restaurantId: Int = 0,
                           @SerializedName("Term") val query: String = "",
                           @SerializedName("ProductCategoryIdList") val categoryIds: ArrayList<Int> = arrayListOf())
