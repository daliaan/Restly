package dalian.razvan.cucer.models.product

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import dalian.razvan.cucer.core.baseClasses.BaseModel
import dalian.razvan.cucer.models.restaurant.Restaurant

data class Product(@SerializedName("id") val id: Int = 0,
                   @SerializedName("title") val title: String = "",
                   @SerializedName("imageUrl") val imageUrl: String = "",
                   @SerializedName("description") val description: String = "",
                   @SerializedName("rating") val rating: Double = 0.0,
                   @SerializedName("price") val price: Double = 0.0,
                   @SerializedName("allergens") val allergens: ArrayList<String> = arrayListOf()): BaseModel(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readDouble(),
            parcel.readDouble(),
            arrayListOf<String>().apply {
                parcel.readArrayList(String::class.java.classLoader)
            }) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeDouble(rating)
        parcel.writeDouble(price)
        parcel.writeList(allergens)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
