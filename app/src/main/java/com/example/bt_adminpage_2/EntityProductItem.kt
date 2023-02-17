package com.example.bt_adminpage_2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableProductItem")
class EntityProductItem {
    @PrimaryKey(autoGenerate = true)       var idProductItem: Int? = -1
    @ColumnInfo(name = "full_nameProdcutItem") var nameProductItem: String =""
    @ColumnInfo(name = "PriceProdcutItem")     var priceItem : String= ""

    constructor(idProductItem: Int?, nameProductItem: String, priceItem: String) {
        this.idProductItem = idProductItem
        this.nameProductItem = nameProductItem
        this.priceItem = priceItem
    }
}
