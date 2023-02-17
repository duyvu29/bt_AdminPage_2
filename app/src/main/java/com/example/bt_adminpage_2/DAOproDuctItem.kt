package com.example.bt_adminpage_2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAOproDuctItem {
    @Insert
    fun addItemProductList(product : EntityProductItem)

    @Query ("SELECT * FROM tableProductItem")
     fun getAllproductList(): List<EntityProductItem>
    @Delete
     fun DeleteProduct(product: EntityProductItem)
    @Query("DELETE FROM tableProductItem WHERE idProductItem = :id")
    fun delete(id : Int?)

}