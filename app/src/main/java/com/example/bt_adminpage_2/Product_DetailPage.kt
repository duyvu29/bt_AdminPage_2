package com.example.bt_adminpage_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.view.View
import android.widget.*
import androidx.room.Room
import com.example.bt_adminpage_2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_product_detail_page.*
import kotlinx.android.synthetic.main.layout_item_product.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Product_DetailPage : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  dbProductItem2:Category_Database
    private var  dbProductItem:Category_Database? = null
    lateinit var imgBack: ImageView
    lateinit var imgDetail: ImageView
    lateinit var txtNameDetail : TextView
    lateinit var txtPrice : TextView
    lateinit var spinnerDeetail: Spinner
    lateinit var btnDetailRemove : Button
    lateinit var btnDetaiSave :  Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_page)


       // binding = ActivityMainBinding.inflate(layoutInflater)
       // setContentView(binding.root)

        //dbProductItem2 = Category_Database.getDatabase(this)



        // ánh xạ
        mappingDetail()
        // hàm detialPage
        setDetailPage()
        // hàm sự kiện
        addEvent()
        //
        spinnerProductItem()
        //init
        initRoomDB()

    }

    private fun addEvent() {
        //
        imgBack.setOnClickListener {
            finish()
        }
        //
        btnDetaiSave.setOnClickListener(View.OnClickListener {
            setProductAddItemDatabasse(dbProductItem!!)
        })
        // xóa dữ liệu
        btnDetailRemove.setOnClickListener{
           /* binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            dbProductItem2 = Category_Database.getDatabase(this)

            GlobalScope.launch {
                dbProductItem!!.callDaoProductItem().DeleteProduct()
               // Toast.makeText(this,"Thành công",Toast.LENGTH_SHORT).show()
            }*/

            var list = ArrayList<Data_itemProduct>()


            Toast.makeText(this,"Thành công",Toast.LENGTH_SHORT).show()
        }
    }

    private fun mappingDetail() {
        imgBack         = findViewById(R.id.imgBackDetail)
        imgDetail       = findViewById(R.id.imgProductDetail)
        txtNameDetail   = findViewById(R.id.txtNameDetailProduct)
        txtPrice        = findViewById(R.id.txtPriceDetailProduct)
        spinnerDeetail  = findViewById(R.id.spinnerProductDetail)
        btnDetaiSave    = findViewById(R.id.btnSaveDetailProduct)
        btnDetailRemove = findViewById(R.id.btnRemoveDetailProduct)
    }
    private fun setDetailPage(){
        var i= intent
        var name = i.getStringExtra("name")
        var price = i.getStringExtra("price")
        var imge = i.getIntExtra("image",0)

        txtNameDetail.text= name
        txtPrice.text = price
        imgDetail.setImageResource(imge)
    }
    private fun spinnerProductItem (){
        // khởi tạo danh sách: List
        var list= ArrayList<String>()
        list.add("Danh mục")
        list.add("Điên thoại")
        list.add("Laptop")

        // khởi tạo adaoter thông thường
        var adt= ArrayAdapter(this, android.R.layout.simple_list_item_1,list)
        // gọi spiner
        spinnerDeetail.adapter=adt
        // sự kiện lắng nghe item trên Spinner
        spinnerDeetail.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                Toast.makeText(this@Product_DetailPage,list[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }
    // hàm save product
    private fun setProductAddItemDatabasse(DatabasePoductItem: Category_Database) {

        val ProductItemDao = DatabasePoductItem.callDaoProductItem()

        var nameProductItem: String = txtNameDetail.text.toString()
        var priceProductItem: String = txtPrice.text.toString()

        var productItem = EntityProductItem(idProductItem = null, nameProductItem = nameProductItem, priceItem = priceProductItem)
        ProductItemDao.addItemProductList(productItem)

        Toast.makeText(this, "Success add Product Iteem List", Toast.LENGTH_SHORT).show()
    }


    // init product database
    private fun initRoomDB() {
        // category_Database duoc khoi tao 1 lan va dung o moi class trong app
        dbProductItem = Room.databaseBuilder(applicationContext, Category_Database::class.java, "staff-db")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }


}