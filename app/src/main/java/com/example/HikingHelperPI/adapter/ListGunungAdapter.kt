package com.example.HikingHelperPI.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.HikingHelperPI.R
import com.example.HikingHelperPI.activities.DetailGunungActivity
import com.example.HikingHelperPI.model.ModelGunung
import com.bumptech.glide.Glide
import com.example.HikingHelperPI.adapter.ListGunungAdapter.ViewHolder
import kotlinx.android.synthetic.main.list_item_gunung.view.*
import java.util.*


//revisi START

class ListGunungAdapter(
    private val context: Context?,
    private val modelGunung: MutableList<ModelGunung>) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_gunung, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelGunung[position]

        if (context != null) {
            Glide.with(context!!)
                .load(data.ImageGunung)
                .into(holder.listimageGunung)
        }


        holder.listNamaGunung.text = data.NamaGunung
        holder.listLokasiGunung.text = data.LokasiGunung

        holder.cvListGunung.setOnClickListener {
            val intent = Intent(context, DetailGunungActivity::class.java)
            intent.putExtra(DetailGunungActivity.DETAIL_GUNUNG, modelGunung[position])
            if (context != null) {
                context!!.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return modelGunung.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListGunung: CardView
        var listimageGunung: ImageView
        var listNamaGunung: TextView
        var listLokasiGunung: TextView

        init {
            cvListGunung = itemView.cvListGunung
            listimageGunung = itemView.imageGunung
            listNamaGunung = itemView.tvNamaGunung
            listLokasiGunung = itemView.tvLokasiGunung
        }
    }

}