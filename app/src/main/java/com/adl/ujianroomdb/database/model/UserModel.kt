package com.adl.adlroomdb.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class UserModel(@PrimaryKey(autoGenerate = true) val id:Long = 0,
                     val usernama:String, val gnder:String,
                     val age:String, val stat:String,
                     ) :Parcelable
