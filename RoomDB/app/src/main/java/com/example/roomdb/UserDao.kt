package com.example.roomdb

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Insert
    fun insertAll(list: List<User>)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
    fun getAllUser(): List<User>

    @Query("Select * From User where age>=:age")
    fun getUserWithAge(age: Int): List<User>

}