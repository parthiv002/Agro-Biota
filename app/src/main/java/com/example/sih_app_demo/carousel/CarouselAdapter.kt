package com.example.sih_app_demo.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sih_app_demo.R

class CarouselAdapter(private val imageList: List<Int>, private val imageDescriptions: List<String>? = null) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carousel_image, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(imageList[position], imageDescriptions?.get(position))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.carousel_image)
       // private val descriptionView: TextView? = itemView.findViewById(R.id.carousel_description) // Optional

        fun bind(imageRes: Int, description: String?) {
            imageView.setImageResource(imageRes)
            //descriptionView?.text = description // Optional
        }
    }
}
