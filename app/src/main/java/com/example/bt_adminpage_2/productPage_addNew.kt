package com.example.bt_adminpage_2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Camera
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.room.Room

class productPage_addNew : AppCompatActivity() {
    private var  dbProduct:Category_Database? = null
    lateinit var spinnerProduct  : Spinner
    lateinit var productPrevious : ImageButton
    lateinit var saveProductData : ImageButton
    lateinit var edtNameProduct  : EditText
    lateinit var edtPriceProduct : EditText
    lateinit var imgCamera       : ImageButton
    lateinit var imgLibrary      : ImageButton
    lateinit var imgBrackgrMain  : ImageView
    var REQUEST_IMAGE_CAPTURE = 100
    var REQUEST_IMAGE_LIBRARY = 111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page_add_new)
        // ánh xạ
        mapping()
        // các sự kiện
        addEventProduct()
        // spinnner
        spinnerProductItem()
        //init
        initRoomDB()
    }

    private fun mapping() {
        spinnerProduct  = findViewById(R.id.spinerProduct)
        productPrevious = findViewById(R.id.imgProductBack)
        saveProductData = findViewById(R.id.imgSaveProduct)
        edtNameProduct  = findViewById(R.id.edtNameProduct)
        edtPriceProduct = findViewById(R.id.edtPriceProduct)
        imgCamera       = findViewById(R.id.imgCameraIcon)
        imgLibrary      = findViewById(R.id.imgLibraryIcon)
        imgBrackgrMain  = findViewById(R.id.imgbackgrProduct)
    }
    private fun addEventProduct(){
        // sự kiện quay về màn hình main
        productPrevious.setOnClickListener(View.OnClickListener {
            finish()
        })
        // chọn hình ảnh từ camera
        imgCamera.setOnClickListener{
            var imageCammera  = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(imageCammera,REQUEST_IMAGE_CAPTURE)

            }catch (e: ActivityNotFoundException){
                Toast.makeText(this ,"Error",Toast.LENGTH_SHORT).show()
            }

        }
        // chọn hình ảnh từ thư viện
        imgLibrary .setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_LIBRARY)
        }
        // hàm lưu product item
        saveProductData.setOnClickListener {
            setProductAddDatabasse(dbProduct!!)
        }

    }
    // spinner
    private fun spinnerProductItem (){
        // khởi tạo danh sách: List
        var list= ArrayList<String>()
        list.add("Danh mục")
        list.add("Điên thoại")
        list.add("Laptop")

        // khởi tạo adaoter thông thường
        var adt= ArrayAdapter(this, android.R.layout.simple_list_item_1,list)
        // gọi spiner
        spinnerProduct.adapter=adt
        // sự kiện lắng nghe item trên Spinner
        spinnerProduct.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                Toast.makeText(this@productPage_addNew,list[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
         if (requestCode == REQUEST_IMAGE_CAPTURE  && resultCode == RESULT_OK ){
             val bundle = data?.extras
             var bitmap: Bitmap = bundle!!.get("data") as Bitmap
             imgBrackgrMain.setImageBitmap(bitmap)

         }
        if (requestCode == REQUEST_IMAGE_LIBRARY && resultCode == RESULT_OK && null != data) {
            val uri: Uri = data.data!!
            imgBrackgrMain.setImageURI(uri)
        }

    }
    // hàm save product
    private fun setProductAddDatabasse(DatabasePoduct: Category_Database) {

        val ProductDao = DatabasePoduct.callDaoProduct()

        var nameProduct: String = edtNameProduct.text.toString()
        var priceProduct: String = edtNameProduct.text.toString()

        var product = Entity_product(idProduct = null, nameProduct = nameProduct, price = priceProduct)
        ProductDao.addItemProduct(product)

        Toast.makeText(this, "Success add Product", Toast.LENGTH_SHORT).show()
    }


    // init product database
    private fun initRoomDB() {
        // category_Database duoc khoi tao 1 lan va dung o moi class trong app
        dbProduct = Room.databaseBuilder(applicationContext, Category_Database::class.java, "staff-db")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

}