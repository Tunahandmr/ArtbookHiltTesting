package com.tunahan.artbookhilttesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.tunahan.artbookhilttesting.getOrAwaitValue
import com.tunahan.artbookhilttesting.model.Art
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var artDao: ArtDao
    private lateinit var artDatabase: ArtDatabase

    @Before
    fun setup() {

        artDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArtDatabase::class.java
        ).allowMainThreadQueries().build()

        artDao = artDatabase.artDao()
    }

    @After
    fun teardown(){

        artDatabase.close()
    }

    @Test
    fun insertArtTesting() = runTest {

        val exampleArt = Art("Tunahan","Demir",2023,"google.com",1)
        artDao.insertArt(exampleArt)

        val list = artDao.observeArts().getOrAwaitValue()
        assertThat(list).contains(exampleArt)
    }

    @Test
    fun deleteArtTesting() = runTest {

        val exampleArt = Art("Tunahan","Demir",2023,"google.com",1)
        artDao.insertArt(exampleArt)
        artDao.deleteArt(exampleArt)

        val list = artDao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)

    }
}