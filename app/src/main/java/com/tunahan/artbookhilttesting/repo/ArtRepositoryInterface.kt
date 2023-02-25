package com.tunahan.artbookhilttesting.repo

import androidx.lifecycle.LiveData
import com.tunahan.artbookhilttesting.model.Art
import com.tunahan.artbookhilttesting.model.ImageResponse
import com.tunahan.artbookhilttesting.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art:Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun imageSearch(imageString: String): Resource<ImageResponse>
}