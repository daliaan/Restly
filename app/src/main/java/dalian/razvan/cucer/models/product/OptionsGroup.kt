package dalian.razvan.cucer.models.product

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import dalian.razvan.cucer.core.baseClasses.BaseModel
import dalian.razvan.cucer.models.restaurant.Restaurant

data class OptionsGroup(@SerializedName("id") val id: Int = 0,
                        @SerializedName("name") val name: String = "",
                        @SerializedName("type") val type: Int = 0,
                        @SerializedName("min") val min: Int = 0,
                        @SerializedName("max") val max: Int = 0,
                        @SerializedName("isRequired") val isRequired: Boolean = false,
                        @SerializedName("options") val options: ArrayList<Option> = arrayListOf()): BaseModel(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString().toString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            arrayListOf<Option>().apply {
                parcel.readArrayList(Option::class.java.classLoader)
            }) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(type)
        parcel.writeInt(min)
        parcel.writeInt(max)
        parcel.writeByte(if (isRequired) 1 else 0)
        parcel.writeList(options)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OptionsGroup> {
        override fun createFromParcel(parcel: Parcel): OptionsGroup {
            return OptionsGroup(parcel)
        }

        override fun newArray(size: Int): Array<OptionsGroup?> {
            return arrayOfNulls(size)
        }
    }
}