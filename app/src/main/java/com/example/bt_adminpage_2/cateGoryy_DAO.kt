package com.example.bt_adminpage_2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface cateGoryy_DAO {
    @Insert
    fun add_cateGory(category : Entity_adminPage)

    @Query ("SELECT * FROM adminPage")
     fun getAllcategory(): List<Entity_adminPage>

}