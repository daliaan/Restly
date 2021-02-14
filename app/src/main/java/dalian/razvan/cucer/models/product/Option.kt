package dalian.razvan.cucer.models.product

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Option(@SerializedName("id") val id: Int = 0,
                  @SerializedName("name") val name: String = "",
                  @SerializedName("price") val price: Double = 0.0): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString().toString(),
            parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Option> {
        override fun createFromParcel(parcel: Parcel): Option {
            return Option(parcel)
        }

        override fun newArray(size: Int): Array<Option?> {
            return arrayOfNulls(size)
        }
    }
}
