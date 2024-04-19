package com.okurapp.okurapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader

class SearchActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Books>
    var tit = mutableListOf<String>()
    var aut = mutableListOf<String>()
    var imageUrl = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var menuBtn: ImageButton = findViewById<ImageButton>(R.id.menuBtn)
        menuBtn.setOnClickListener {
            val switchMenu = Intent(applicationContext, Menu::class.java)
            startActivity(switchMenu)
        }

        var searchText = intent.getStringExtra("search_text")
        val text = findViewById<TextView>(R.id.searchText)
        text.text = searchText

        var backBtn : ImageButton = findViewById(R.id.backBtn)

        backBtn.setOnClickListener {
            val switchBack = Intent(applicationContext, MainWelcome::class.java)
            startActivity(switchBack)
        }

        var profilBtn: ImageButton = findViewById(R.id.accountImage)
        profilBtn.setOnClickListener {
            val switchSearch = Intent(applicationContext, mertMain::class.java)
            startActivity(switchSearch)
        }

        val inputStream = assets.open("books.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))

        val bookList = mutableListOf<List<String>>()

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            val row = line?.split(";")
            bookList.add(row ?: listOf())
        }
        reader.close()



        for(i in 0..239){
            if(bookList[i] != null) {
                if(bookList[i].get(0).lowercase().contains(searchText.toString().lowercase()) == true
                    || bookList[i].get(2).lowercase().contains(searchText.toString().lowercase()) == true) {
                    tit.add(bookList[i].get(0).drop(1))
                    aut.add(bookList[i].get(2))
                    imageUrl.add(bookList[i].get(8))
                }
            }else {
                break
            }
        }

        newRecyclerView = findViewById(R.id.recyclerView)
        var layoutManager = LinearLayoutManager(this)
        newRecyclerView.layoutManager = layoutManager
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Books>()

        getUserData()

    }


    private fun getUserData() {
        val size = tit.size
        for(i in 0..size - 1){
            val books = Books(tit[i], aut[i], imageUrl[i])
            newArrayList.add(books)
        }
        newRecyclerView.adapter = MyAdapter(newArrayList)
    }
}