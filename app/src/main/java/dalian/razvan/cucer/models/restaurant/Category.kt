package dalian.razvan.cucer.models.restaurant

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import dalian.razvan.cucer.core.baseClasses.BaseModel

data class Category(@SerializedName("id") val id: Int = 0,
                    @SerializedName("title") val title: String = "",
                    @SerializedName("imageUrl") val imageUrl: String = ""): BaseModel(), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    var isSelected = false

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}
