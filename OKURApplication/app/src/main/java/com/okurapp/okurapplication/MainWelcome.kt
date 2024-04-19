package com.okurapp.okurapplication

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.io.BufferedReader
import kotlin.random.Random

class MainWelcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_welcome)

        val dataList = mutableListOf<List<String>>()

        BufferedReader(assets.open("books.csv").reader()).use { reader ->
            var line = reader.readLine()
            while (line != null) {
                val data = line.split(";")
                dataList.add(data)
                line = reader.readLine()
            }
        }

        var menuBtn: ImageButton = findViewById(R.id.menuBtn)
        menuBtn.setOnClickListener {
            val switchMenu = Intent(applicationContext, Menu::class.java)
            startActivity(switchMenu)
        }

        var profilBtn: ImageButton = findViewById(R.id.accountImage)
        profilBtn.setOnClickListener {
            val switchSearch = Intent(applicationContext, mertMain::class.java)
            startActivity(switchSearch)
        }

        val searchText: EditText = findViewById(R.id.editTextText)
        val searchBtn: ImageButton = findViewById(R.id.searchBtn)

        searchBtn.setOnClickListener {
            val switchSearch = Intent(applicationContext, SearchActivity::class.java)
            switchSearch.putExtra("search_text", searchText.text.toString())
            startActivity(switchSearch)
        }
        val titleIds = listOf(
            R.id.booktitle1, R.id.booktitle2, R.id.booktitle3, R.id.booktitle4, R.id.booktitle5,
            R.id.booktitle6, R.id.booktitle7, R.id.booktitle8, R.id.booktitle9
        )
        val authorIds = listOf(
            R.id.bookauthor1, R.id.bookauthor2, R.id.bookauthor3, R.id.bookauthor4, R.id.bookauthor5,
            R.id.bookauthor6, R.id.bookauthor7, R.id.bookauthor8, R.id.bookauthor9
        )
        val imageIds = listOf(
            R.id.bookimage1, R.id.bookimage2, R.id.bookimage3, R.id.bookimage4, R.id.bookimage5,
            R.id.bookimage6, R.id.bookimage7, R.id.bookimage8, R.id.bookimage9
        )
        var images= mutableListOf<ImageButton>()

        val tit = mutableListOf<String>()
        val aut = mutableListOf<String>()
        for(i in 0..8) {
            val title = findViewById<TextView>(titleIds[i])
            val author = findViewById<TextView>(authorIds[i])
            val image = findViewById<ImageButton>(imageIds[i])
            val random = Random.nextInt(0, 239)
            val data = dataList[random]
            tit.add(data[0].drop(1))
            aut.add(data[2])
            title.text = data[0].drop(1)
            author.text = data[2]
            val imageUrl = data[8]
            Glide.with(this)
                .load(imageUrl)
                .into(image)
        }

        for (i in 0..8){
            images.add(findViewById(imageIds[i]))
        }

        //var resim1: ImageButton = findViewById(R.id.bookimage1)
        for (i in 0..8) {
            images[i].setOnClickListener {
                val switchBook = Intent(applicationContext, DisplayBook::class.java)
                switchBook.putExtra("title", tit[i])
                startActivity(switchBook)
            }
        }
    }
}