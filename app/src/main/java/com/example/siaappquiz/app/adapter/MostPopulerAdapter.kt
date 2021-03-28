package com.example.siaappquiz.app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.siaappquiz.R
import com.example.siaappquiz.app.models.Metadaum
import com.example.siaappquiz.app.models.ResultResp
import com.example.siaappquiz.databinding.ItemNyPopularBinding
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_ny_popular.view.*

class MostPopulerAdapter(var context: Context, var list: ArrayList<ResultResp>) :
    RecyclerView.Adapter<MostPopulerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(p0.context),
                R.layout.item_ny_popular,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = list[position]
        holder.binding.model = data


        if (list.get(position).media.toString() == "[]" || list.get(position).media.toString().isNullOrEmpty()) {
            Glide.with(context).load(context.resources.getDrawable(R.drawable.random_imag))
                .into(holder.itemView.circler_image)
        } else{
            Glide.with(context).load(list.get(position).media.get(0).media.get(0).url)
                .into(holder.itemView.circler_image)
        }

        // --- tap on item .....
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "" + list.get(position).adx_keywords, Toast.LENGTH_SHORT).show()
        }
    }

    inner class ViewHolder(val binding: ItemNyPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var image: CircleImageView = itemView.findViewById<CircleImageView>(R.id.circler_image)
    }

}