package com.example.chefmate.core.domain.util.imageManager

interface ImageManager {
    suspend fun cacheImage(imageUrl: String): String?
    fun deleteImageFile(imagePath: String)
}