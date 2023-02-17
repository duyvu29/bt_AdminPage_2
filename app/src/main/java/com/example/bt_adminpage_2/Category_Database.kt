package com.example.bt_adminpage_2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf( Entity_adminPage::class,Entity_product::class ,EntityProductItem::class), version = 3,exportSchema = false)
abstract class Category_Database : RoomDatabase() {
    abstract fun callDAO(): cateGoryy_DAO
    abstract fun callDaoProduct(): product_DAO
    abstract fun callDaoProductItem(): DAOproDuctItem

    companion object{

        @Volatile
        private var INSTANCE : Category_Database? = null

        fun getDatabase(context: Context): Category_Database{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Category_Database::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }

}
