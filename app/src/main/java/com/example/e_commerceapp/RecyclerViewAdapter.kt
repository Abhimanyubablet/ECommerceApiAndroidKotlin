package com.example.e_commerceapp
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerViewAdapter(val context: Context, val datalist:List<Attribute>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val colorImage=itemView.findViewById<CircleImageView>(R.id.web_url)


    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.color_list,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data =datalist[position]
        Glide.with(context).load(data.images?.get(0)).placeholder(R.drawable.radhekrishan)
        .into(holder.colorImage)
    }

}