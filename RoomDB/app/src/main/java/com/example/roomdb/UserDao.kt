package com.example.roomdb

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Insert
    fun insertAll(list: List<User>)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
   suspend fun getAllUser(): List<User>

    @Query("Select * From User where age>=:age")
    fun getUserWithAge(age: Int): List<User>

}