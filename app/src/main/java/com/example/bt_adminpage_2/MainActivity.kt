package com.example.bt_adminpage_2

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item_product.*

class MainActivity : AppCompatActivity(),Adapter_itemProduct.ItemOnclicklistener {
    lateinit var filtrAdapter: ArrayAdapter<String>
    lateinit var lv_filter:ListView
    lateinit var rcv_CategoryList: RecyclerView
    lateinit var rcv_ItemList : RecyclerView
    lateinit var filter : ImageView
    lateinit var navi_main: NavigationView
    lateinit var Toogle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    private var  dbProduct:Category_Database? = null
    private val ProductDao: ProductDao = null
   // lateinit var txtNamProductItem : TextView
    //lateinit var txtPriceProductItem: TextView
    //private  var db: Category_Database? = null
    // khái báp adapter và list cho chức năng click item trong recyclerView
   lateinit var list_Itemproduct: ArrayList<Entity_product>
   var listProducts: ArrayList<Entity_product> = ArrayList();
    var adapter_itemProduct = Adapter_itemProduct(listProducts,this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbProduct = Room.databaseBuilder(applicationContext, Category_Database::class.java, "staff-db")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val ProductDao = dbProduct!!.callDaoProduct()
        // ánh xạ
        mapping()
        // roomData
        //initRoomDB()
        // hàm catGory
        setCategory_rcv()
        //hàm productList
        setList_product()
        // hàm sự kiện
        addEvent()
        // hàm chức năng
        setFilter()
        // navigation
        setNaavigation()



    }
     // ANH1 XẠ
     fun mapping(){
        // txtNamProductItem  = findViewById(R.id.txt_nameProduct)
         //txtPriceProductItem = findViewById(R.id.txt_priceproduct)
         rcv_CategoryList  = findViewById(R.id.rcv_Category)
         rcv_ItemList      = findViewById(R.id.rcv_listItem)
         filter             = findViewById(R.id.img_Filter)
         lv_filter          = findViewById(R.id.lv_Filter)
         navi_main          = findViewById(R.id.navigtion_main)
         drawerLayout       = findViewById(R.id.draer_layout)


    }
    /*private fun initRoomDB() {
        // StaffDatabase duoc khoi tao 1 lan va dung o moi class trong app
        db = Room.databaseBuilder(applicationContext, Category_Database::class.java, "staff-db")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }*/
    // NAVIGATION
    private fun setNaavigation(){
        Toogle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(Toogle)
        Toogle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }
    // ADAPTER CATEGORY
    private fun setCategory_rcv(){
        // Tạo danh mục : Category
        var list_cateGory = ArrayList<Data_cateGory>()
        list_cateGory.add(Data_cateGory("Diện thoại",R.drawable.img))
        list_cateGory.add(Data_cateGory("Laptop",R.drawable.laptopp))
        list_cateGory.add(Data_cateGory("Diện thoại",R.drawable.img))
        list_cateGory.add(Data_cateGory("Laptop",R.drawable.laptopp))
        list_cateGory.add(Data_cateGory("Diện thoại",R.drawable.img))
        list_cateGory.add(Data_cateGory("Laptop",R.drawable.laptopp))

        // tạo Adapter cho Category
        val adapter_Category = Adapter_cateGory(list_cateGory);
        rcv_CategoryList.adapter= adapter_Category

        rcv_CategoryList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }
    //ADAPTER LIST PRODUCT
    private fun setList_product(){
        // Tạo danh sách sản phẩm
       // var list_Itemproduct = ArrayList<Data_itemProduct>()
        /*list_Itemproduct.add(Data_itemProduct("Dell XPS 15","30.000.000",R.drawable.dell))
        list_Itemproduct.add(Data_itemProduct("Dell XPS 17","35.000.000",R.drawable.dell17))
        list_Itemproduct.add(Data_itemProduct("Macbook Pro 13","29.000.000",R.drawable.mb13))
        list_Itemproduct.add(Data_itemProduct("Macbook Pro 16","40.000.000",R.drawable.mb16pro))
        list_Itemproduct.add(Data_itemProduct("Macbook Air M2","29.000.000",R.drawable.macm2air))
        list_Itemproduct.add(Data_itemProduct("Oppo Note 7","6.999.999",R.drawable.ooppo))
        list_Itemproduct.add(Data_itemProduct("Xiaomi 8","5.200.000",R.drawable.xiaomi))
        list_Itemproduct.add(Data_itemProduct("Samsum Galaxy","3.200.000",R.drawable.samsum))
        list_Itemproduct.add(Data_itemProduct("Iphone 14 Pro Max","35.000.000",R.drawable.i14))
        list_Itemproduct.add(Data_itemProduct("Iphone 14 Pro Max","35.000.000",R.drawable.laptopp))*/

        list_Itemproduct  = ArrayList();
        list_Itemproduct = ProductDao.getAllproduct() as ArrayList<Entity_product>;
        for (i in 0 until list_Itemproduct.size) {
            listProducts.add(Entity_product(list_Itemproduct[i].idProduct, list_Itemproduct[i].nameProduct)) // lay them cac field khac
        }



        // tạo Adapter cho sàn ph
        //val adapter_itemProduct = Adapter_itemProduct(list_Itemproduct,this);
        rcv_ItemList.adapter= adapter_itemProduct

        rcv_ItemList.layoutManager = GridLayoutManager(this, 2)


    }
    // HÀM SỰ KIỆN CLICK
    private fun addEvent(){
        // sự kiện click filter
        var number: Int = 0
        filter.setOnClickListener {
            number++
            if (number==1){
                // showw list
                lv_filter.visibility= View.VISIBLE
            }
            else{
                // ẩn list
                lv_filter.visibility= View.GONE
            }
            if (number==2){
                number = 0
            }
        }

        // sự kiện navigation click button
        navigation()

    }

    private fun setFilter(){
           var list_filter = ArrayList<String>()
           list_filter.add("Điện thoại")
           list_filter.add("Laptop")
           list_filter.add("Tất cả")

        filtrAdapter  = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list_filter)
        lv_filter.adapter = filtrAdapter

        // chức năng click Item listvieew

        lv_filter.setOnItemClickListener(object  : OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MainActivity, " $p2  ${list_filter[p2]}", Toast.LENGTH_SHORT).show()
                for (i in 0 until list_filter.size) {
                    if (p2== i) {
                        val view1: View = lv_filter.getChildAt(
                            i
                        )
                        view1.setBackgroundResource(R.color.purple_200)
                    } else {
                        val view1: View = lv_filter.getChildAt(
                            i
                        )
                        view1.setBackgroundResource(R.color.white)
                    }
                }
            }
        })



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    // các chức năng trong navigation
    private fun navigation(){
        // ánh xạ màn hình cateGory

        var btn_addItemm = navigtion_main.getHeaderView(0).findViewById<Button>(R.id.btn_addPItemProcduct)
        var btn_addItemCaterogy = navigtion_main.getHeaderView(0).findViewById<Button>(R.id.btn_addCategoru)
        // Click nút thêm item trong danh sách
        btn_addItemm.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "1", Toast.LENGTH_SHORT).show()
            var i = Intent(this, productPage_addNew::class.java)
            startActivity(i)


        })
        // click nút thêm danh mục
        btn_addItemCaterogy.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "2", Toast.LENGTH_SHORT).show()
            val i2= Intent(this, cateGory_layout::class.java)
            startActivity(i2)
        })

    }
    // CLICK ITEM RECYCLERVIEW
    override fun Onitemclick(position: Int) {
        var txtNamProductItem   : TextView =linealayoutItem.findViewById(R.id.txt_nameProduct)
        var txtPriceProductItem : TextView = linealayoutItem.findViewById(R.id.txt_priceproduct)

        val model  = list_Itemproduct.get(position)

        var name: String= model.name_Product

        var price: String = model.price_Product

        var img: Int    = model.img_Product
        var id: Int = model.


       var i = Intent (this,Product_DetailPage::class.java)

        i.putExtra("name",name)
        i.putExtra("price",price)
        i.putExtra("image",img)

        startActivity(i)


    }


}


