package uz.mbr.newstagram.data.model.news

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SourceResponse(

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String
) : Serializable, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SourceResponse> {
        override fun createFromParcel(parcel: Parcel): SourceResponse {
            return SourceResponse(parcel)
        }

        override fun newArray(size: Int): Array<SourceResponse?> {
            return arrayOfNulls(size)
        }
    }
}