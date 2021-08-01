package site.edkw.pikabu.repositories

import android.graphics.Bitmap

interface ImageRepository {

    suspend fun getImage(url: String): Bitmap?

    suspend fun getImages(urls: List<String>): List<Bitmap>
}