package com.example.HikingHelperPI.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.HikingHelperPI.R
import com.bumptech.glide.Glide
import com.example.HikingHelperPI.activities.DetailItineraryActivity
import com.example.HikingHelperPI.model.ModelItinerary
import kotlinx.android.synthetic.main.list_item_itinerary.view.*



class ItineraryAdapter(private val context: Context?, private val modelItinerary:
List<ModelItinerary>) : RecyclerView.Adapter<ItineraryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_itinerary, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelItinerary[position]

        if (context != null) {
            Glide.with(context)
                .load(data.ImageItinerary)
                .into(holder.listimageItinerary)
        }


        holder.listNamaItinerary.text = data.NamaItinerary
        holder.listTipeItinerary.text = data.TipeItinerary

        holder.cvListItinerary.setOnClickListener {
            val intent = Intent(context, DetailItineraryActivity::class.java)
            intent.putExtra(DetailItineraryActivity.DETAIL_ITINERARY, modelItinerary[position])
            if (context != null) {
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return modelItinerary.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListItinerary: CardView
        var listimageItinerary: ImageView
        var listNamaItinerary: TextView
        var listTipeItinerary: TextView

        init {
            cvListItinerary = itemView.cvListItinerary
            listimageItinerary = itemView.imageItinerary
            listNamaItinerary = itemView.tvNamaItinerary
            listTipeItinerary = itemView.tvTipeItinerary
        }
    }

}