package dalian.razvan.cucer.models.restaurant

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Restaurant(@SerializedName("categories") val categoriesString: String = "",
                      @SerializedName("rating") val rating: Double = 0.0,
                      @SerializedName("id") val id: Int = 0,
                      @SerializedName("title") val title: String = "",
                      @SerializedName("imageUrl") val imageUrl: String = ""): Parcelable {
    var categories = arrayListOf<String>()

    init {
        categories = ArrayList(categoriesString.split(","))
    }

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(categoriesString)
        parcel.writeDouble(rating)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Restaurant> {
        override fun createFromParcel(parcel: Parcel): Restaurant {
            return Restaurant(parcel)
        }

        override fun newArray(size: Int): Array<Restaurant?> {
            return arrayOfNulls(size)
        }
    }

}
