package com.example.chefmate.core.domain.util.imageManager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.imageLoader
import coil.request.ImageRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class ImageCacheManager @Inject constructor(
    @ApplicationContext private val context: Context
) : ImageManager {
    override suspend fun cacheImage(imageUrl: String): String? {
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()

        val result = request.context.imageLoader.execute(request).drawable

        return result?.let {
            val bitmap = (it as BitmapDrawable).bitmap
            val file = File(context.cacheDir, "recipe_${System.currentTimeMillis()}.png")
            file.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            }
            file.absolutePath
        }
    }

    override fun deleteImageFile(imagePath: String) {
        val file = File(context.filesDir, imagePath)
        if (file.exists()) {
            file.delete()
        }
    }
}