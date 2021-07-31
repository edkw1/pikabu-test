package site.edkw.pikabu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Post(
    val id: Int = 0,
    val title: String = "",
    val images: ArrayList<String>? = null,
    val body: String = ""
): Parcelable{
}