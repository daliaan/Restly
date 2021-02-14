package dalian.razvan.cucer.core.data.network.model.response.products

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import dalian.razvan.cucer.models.product.Product
import dalian.razvan.cucer.models.restaurant.Restaurant
import java.util.ArrayList

data class ProductsResponseData(@SerializedName("totalPages") val totalPages: Int,
                                @SerializedName("currentPage") val currentPage: Int,
                                @SerializedName("activeIndex") val activeIndex: Int,
                                @SerializedName("totalElementCount") val totalElementCount: Int,
                                @SerializedName("data") val products: ArrayList<Product> = arrayListOf()): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            arrayListOf<Product>().apply {
                parcel.readArrayList(Product::class.java.classLoader)
            }) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(totalPages)
        parcel.writeInt(currentPage)
        parcel.writeInt(activeIndex)
        parcel.writeInt(totalElementCount)
        parcel.writeList(products)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductsResponseData> {
        override fun createFromParcel(parcel: Parcel): ProductsResponseData {
            return ProductsResponseData(parcel)
        }

        override fun newArray(size: Int): Array<ProductsResponseData?> {
            return arrayOfNulls(size)
        }
    }
}
