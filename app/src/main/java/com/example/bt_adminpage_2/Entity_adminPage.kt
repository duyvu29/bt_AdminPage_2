package com.example.bt_adminpage_2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adminPage")
class Entity_adminPage {
    @PrimaryKey(autoGenerate = true) var id: Int? = -1
    @ColumnInfo(name = "full_name") var fullname: String =""
    @ColumnInfo(name = "descCategory") var descCategory : String= ""

    constructor(id: Int?, fullname: String, descCategory: String) {
        this.id = id
        this.fullname = fullname
        this.descCategory = descCategory
    }
}
