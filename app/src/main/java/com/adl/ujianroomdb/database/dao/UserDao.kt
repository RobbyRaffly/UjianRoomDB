package com.adl.adlroomdb.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adl.adlroomdb.database.model.UserModel


@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserModel)

    @Delete
    fun deleteUser(user: UserModel)

    @Query("update UserModel set usernama=:unam,gnder=:gnd, age=:umr, stat=:stts where id=:idusr")
    fun updateUser(idusr:Long, unam:String, gnd:String, umr:String, stts:String)
//    @Update
//    fun updateUser(user:UserModel):Int

    @Query("SELECT * FROM UserModel")
    fun getAll(): List<UserModel>

    @Query("Select count(*) from UserModel")
    fun getCount():Int
}