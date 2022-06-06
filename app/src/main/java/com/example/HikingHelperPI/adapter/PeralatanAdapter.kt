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
import com.example.HikingHelperPI.activities.DetailPeralatanActivity
import com.example.HikingHelperPI.model.ModelPeralatan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_peralatan_tips.view.*

/**
 * Created by Azhar Rivaldi on 03-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class PeralatanAdapter(private val context: Context?, private val modelPeralatan:
List<ModelPeralatan>) : RecyclerView.Adapter<PeralatanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_peralatan_tips, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelPeralatan[position]

        if (context != null) {
            Glide.with(context)
                .load(data.strImagePeralatan)
                .into(holder.imagePeralatan)
        }


        holder.tvNamaAlat.text = data.strNamaPeralatan
        holder.tvTipeAlat.text = data.strTipePeralatan

        holder.cvListPeralatan.setOnClickListener {
            val intent = Intent(context, DetailPeralatanActivity::class.java)
            intent.putExtra(DetailPeralatanActivity.DETAIL_PERALATAN, modelPeralatan[position])
            if (context != null) {
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return modelPeralatan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListPeralatan: CardView
        var imagePeralatan: ImageView
        var tvNamaAlat: TextView
        var tvTipeAlat: TextView

        init {
            cvListPeralatan = itemView.cvListPeralatan
            imagePeralatan = itemView.imagePeralatan
            tvNamaAlat = itemView.tvNamaAlat
            tvTipeAlat = itemView.tvTipeAlat
        }
    }

}