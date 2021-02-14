package dalian.razvan.cucer.core.data.network.model.response.restaurants

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import dalian.razvan.cucer.models.restaurant.Restaurant
import java.util.ArrayList

data class RestaurantsResponseData(@SerializedName("totalPages") val totalPages: Int,
                                   @SerializedName("currentPage") val currentPage: Int,
                                   @SerializedName("activeIndex") val activeIndex: Int,
                                   @SerializedName("totalElementCount") val totalElementCount: Int,
                                   @SerializedName("data") val restaurants: ArrayList<Restaurant> = arrayListOf()): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        arrayListOf<Restaurant>().apply {
            parcel.readArrayList(Restaurant::class.java.classLoader)
        }
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(totalPages)
        parcel.writeInt(currentPage)
        parcel.writeInt(activeIndex)
        parcel.writeInt(totalElementCount)
        parcel.writeList(restaurants)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RestaurantsResponseData> {
        override fun createFromParcel(parcel: Parcel): RestaurantsResponseData {
            return RestaurantsResponseData(parcel)
        }

        override fun newArray(size: Int): Array<RestaurantsResponseData?> {
            return arrayOfNulls(size)
        }
    }
}
