package com.example.bt_adminpage_2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room

class cateGory_layout : AppCompatActivity() {
    private var db: Category_Database? = null
    var categoryDatabase: Category_Database? = null
    lateinit var btn_previousCategory: Button
    lateinit var btn_saveCategory: Button
    lateinit var edt_nameCategory: TextView
    lateinit var edt_DescCategory: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cate_gory_layout)
        // ánh xạ :Maping
        mapping_cateGory()
        // hàm các sự kiện
        addEvent_category()
        // lưu thông tin vào Database
        initRoomDB()

    }

    fun mapping_cateGory() {
        btn_previousCategory = findViewById(R.id.btn_previous_Category)
        btn_saveCategory = findViewById(R.id.btn_save_Category)
        edt_nameCategory = findViewById(R.id.edt_NameItem_Category)
        edt_DescCategory = findViewById(R.id.edt_descCategory)
    }

    private fun addEvent_category() {
        // sự kiện quay về màn hình main
        btn_previousCategory.setOnClickListener(View.OnClickListener {
            finish()

        })
        // sự kiện lưu vào Data base
        btn_saveCategory.setOnClickListener(View.OnClickListener {

            setText_cateGory(db!!)
            //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        })
    }


    private fun setText_cateGory(Databas_category: Category_Database) {

        val categoryDao = Databas_category.callDAO()

        var name: String = edt_nameCategory.text.toString()
        var desc: String = edt_DescCategory.text.toString()

        var category = Entity_adminPage(id = null, fullname = name, descCategory = desc)
        categoryDao.add_cateGory(category)

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }

    private fun initRoomDB() {
        // category_Database duoc khoi tao 1 lan va dung o moi class trong app
        db = Room.databaseBuilder(applicationContext, Category_Database::class.java, "staff-db")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }
}