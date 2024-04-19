package com.okurapp.okurapplication

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter (private val newList: ArrayList<Books>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.book,parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context)
            .load(currentItem.imageUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val cardView: CardView = itemView.findViewById(R.id.cardView)
        init {
            cardView.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val intent = Intent(itemView.context, DisplayBook::class.java)
                    intent.putExtra("title", title.text)
                    itemView.context.startActivity(intent)
                }
            }
        }


        val title : TextView = itemView.findViewById(R.id.booktitle)
        val author : TextView = itemView.findViewById(R.id.bookauthor)
        val image : ImageButton = itemView.findViewById(R.id.bookimage)
    }
}