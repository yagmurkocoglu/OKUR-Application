package com.okurapp.okurapplication

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {

    private lateinit var categoryListView: ListView

    private val categories = arrayOf("Fiction", "Fantasy", "Classics", "Young Adult", "Novels"
        , "Historical", "Historical Finction", "Literature", "Romance", "Adventure", "Science Finction")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val back = findViewById<Button>(R.id.mainPage)
        back.setOnClickListener {
            val switchMain = Intent(applicationContext, MainWelcome::class.java)
            startActivity(switchMain)
        }

        categoryListView = findViewById(R.id.categoryListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categories)
        categoryListView.adapter = adapter

        val listView: ListView = findViewById(R.id.categoryListView)

        listView.onItemClickListener = AdapterView.OnItemClickListener {parent, view, position, id->
            val intent = Intent(this, Category::class.java)
            if (position in categories.indices) {
                intent.putExtra("genre", categories[position])
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid option selected.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}