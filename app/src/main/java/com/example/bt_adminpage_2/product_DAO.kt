package com.example.bt_adminpage_2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
    fun addItemProduct(product : Entity_product)

    @Query ("SELECT * FROM tableProduct")
     fun getAllproduct(): List<Entity_product>

}