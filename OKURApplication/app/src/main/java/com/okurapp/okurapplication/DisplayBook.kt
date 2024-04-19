package com.okurapp.okurapplication

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import java.io.BufferedReader

class DisplayBook : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_book)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val t = intent.getStringExtra("title")
        title = t.toString()

        val dataList = mutableListOf<List<String>>()

        BufferedReader(assets.open("books.csv").reader()).use { reader ->
            var line = reader.readLine()
            while (line != null) {
                val data = line.split(";")
                dataList.add(data)
                line = reader.readLine()
            }
        }
        for(i in 0..239){
            if(dataList[i] != null) {
                if(dataList[i].get(0).lowercase().contains(t.toString().lowercase()) == true) {
                    val titleView = findViewById<TextView>(R.id.titleView)
                    titleView.text = dataList[i].get(0).drop(1)

                    val seriesView = findViewById<TextView>(R.id.seriesView)
                    seriesView.text = dataList[i].get(1)

                    val authorView = findViewById<TextView>(R.id.authorView)
                    authorView.text = dataList[i].get(2)

                    val descriptionView = findViewById<TextView>(R.id.descriptionView)
                    descriptionView.text = dataList[i].get(3)

                    val genreView = findViewById<TextView>(R.id.genreView)
                    genreView.text = dataList[i].get(4)

                    val pageView = findViewById<TextView>(R.id.pageView)
                    pageView.text = dataList[i].get(5)

                    val publisherView = findViewById<TextView>(R.id.publisherView)
                    publisherView.text = dataList[i].get(6)

                    val publishedDateView = findViewById<TextView>(R.id.publishedDateView)
                    publishedDateView.text = dataList[i].get(7)

                    val imageUrl = dataList[i].get(8)

                    val imageView: ImageView = findViewById(R.id.coverView)
                    Glide.with(this)
                        .load(imageUrl)
                        .into(imageView)
                }
            }else {
                break
            }
        }
    }
}