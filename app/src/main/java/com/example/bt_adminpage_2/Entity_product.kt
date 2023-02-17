package com.example.bt_adminpage_2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableProduct")
class Entity_product {
    @PrimaryKey(autoGenerate = true)       var idProduct: Int? = -1
    @ColumnInfo(name = "full_nameProdcut") var nameProduct: String =""
    @ColumnInfo(name = "PriceProdcut")     var price : String= ""

    constructor(idProduct: Int?, nameProduct: String, price: String) {
        this.idProduct = idProduct
        this.nameProduct = nameProduct
        this.price = price
    }
}
