package com.svoboda.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.svoboda.database.data.AppDatabase
import com.svoboda.database.data.FavoriteProductDao
import com.svoboda.database.domain.model.FavoriteProduct
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FavoriteProductDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var productDao: FavoriteProductDao

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        productDao = database.productDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun testGetAllFavoriteProductIds() = runBlocking {
        val favoriteProduct1 = FavoriteProduct(1)
        val favoriteProduct2 = FavoriteProduct(2)

        productDao.insertOrUpdate(favoriteProduct1)
        productDao.insertOrUpdate(favoriteProduct2)

        val favoriteProductIds = productDao.getAllFavoriteProductIds().first()
        assertEquals(listOf(1, 2), favoriteProductIds)
    }

    @Test
    @Throws(Exception::class)
    fun testGetProductById() = runBlocking {
        val favoriteProduct = FavoriteProduct(1)
        productDao.insertOrUpdate(favoriteProduct)

        val foundProduct = runBlocking { productDao.getProductById(1).first() }
        assertEquals(1, foundProduct)
    }

    @Test
    @Throws(Exception::class)
    fun testGetProductByIdNotFound() = runBlocking {
        val foundProduct = runBlocking { productDao.getProductById(100).first() }
        assertNull(foundProduct)
    }

    @Test
    @Throws(Exception::class)
    fun testInsertOrUpdate() = runBlocking {
        val favoriteProduct = FavoriteProduct(1)
        productDao.insertOrUpdate(favoriteProduct)

        val favoriteProductIds = runBlocking { productDao.getAllFavoriteProductIds().first() }
        assertEquals(listOf(1), favoriteProductIds)
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() = runBlocking {
        val favoriteProduct = FavoriteProduct(1)
        productDao.insertOrUpdate(favoriteProduct)
        productDao.delete(favoriteProduct)

        val favoriteProductIds = runBlocking { productDao.getAllFavoriteProductIds().first() }
        assertEquals(emptyList<Int>(), favoriteProductIds)
    }
}
