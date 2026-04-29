package kz.diploma.securefinance.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kz.diploma.securefinance.data.entity.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllLive(): LiveData<List<Category>>

    @Query("SELECT * FROM categories ORDER BY name ASC")
    suspend fun getAll(): List<Category>

    @Query("SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    fun getByTypeLive(type: String): LiveData<List<Category>>

    @Query("SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    suspend fun getByType(type: String): List<Category>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getById(id: Long): Category?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<Category>)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT COUNT(*) FROM categories")
    suspend fun getCount(): Int
}
