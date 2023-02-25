package com.tunahan.artbookhilttesting.repo

import androidx.lifecycle.LiveData
import com.tunahan.artbookhilttesting.api.RetrofitAPI
import com.tunahan.artbookhilttesting.model.Art
import com.tunahan.artbookhilttesting.model.ImageResponse
import com.tunahan.artbookhilttesting.roomdb.ArtDao
import com.tunahan.artbookhilttesting.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
): ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun imageSearch(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error("No data!",null)
        }
    }
}