package com.example.chefmate.core.domain.util

import com.example.chefmate.core.domain.util.imageManager.ImageManager

class FakeImageManager : ImageManager {

    private val fakeImageStore = mutableMapOf<String, String>()
    private var currentTime = 0L

    override suspend fun cacheImage(imageUrl: String): String {
        currentTime += 1000
        val fakeImagePath = "fake_cache_dir/recipe_$currentTime.png"
        fakeImageStore[imageUrl] = fakeImagePath
        return fakeImagePath
    }

    override fun deleteImageFile(imagePath: String) {
        fakeImageStore.entries.removeIf { it.key == imagePath }
    }

    fun isImageCached(imageUrl: String): Boolean {
        return fakeImageStore.containsKey(imageUrl)
    }
}
