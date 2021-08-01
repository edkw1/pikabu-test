package site.edkw.pikabu.repositories.impl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import site.edkw.pikabu.repositories.ImageRepository
import java.net.URL
import java.net.URLConnection
import javax.inject.Inject


class ImageRepositoryImpl @Inject constructor() : ImageRepository {

    override suspend fun getImage(url: String): Bitmap? {
        try {
            val urlObj = URL(url)
            val conn: URLConnection = urlObj.openConnection()
            return BitmapFactory.decodeStream(conn.getInputStream())
        } catch (e: Exception) {
            Log.e(
                ImageRepositoryImpl::class.java.simpleName,
                "Image loading error: $url | ${e.stackTraceToString()}"
            )
        }
        return null
    }

    override suspend fun getImages(urls: List<String>): List<Bitmap> {
        val successLoadedImages = mutableListOf<Bitmap>()
        urls.forEach {
            val bitmap = getImage(it)
            if (bitmap != null) {
                successLoadedImages.add(bitmap)
            }
        }
        return successLoadedImages
    }
}