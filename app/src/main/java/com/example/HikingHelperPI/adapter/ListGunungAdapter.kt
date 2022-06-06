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
    private val modelGunung: MutableList<ModelGunung>) : RecyclerView.Adapter<ViewHolder>(), Filterable {

    private val modelGunungFilterList: List<ModelGunung> = ArrayList(modelGunung)


    @ExperimentalStdlibApi
    override fun getFilter(): Filter {
        return modelGunungFilter
    }


    @ExperimentalStdlibApi
    private val modelGunungFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ModelGunung> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(modelGunungFilterList)
            } else {
                val filterPattern = constraint.toString().lowercase().trim { it <= ' ' }
                for (modelGunungFilter in modelGunungFilterList) {
                    if (modelGunungFilter.strNamaGunung!!.lowercase().contains(filterPattern))
                    {
                        filteredList.add(modelGunungFilter)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            modelGunung.clear()
            modelGunung.addAll(results.values as List<ModelGunung>)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_gunung, parent, false)
        return ViewHolder(view)
    }
//REvisi END
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelGunung[position]

        if (context != null) {
            Glide.with(context!!)
                .load(data.strImageGunung)
                .into(holder.imageGunung)
        }


        holder.tvNamaGunung.text = data.strNamaGunung
        holder.tvLokasiGunung.text = data.strLokasiGunung

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
        var imageGunung: ImageView
        var tvNamaGunung: TextView
        var tvLokasiGunung: TextView

        init {
            cvListGunung = itemView.cvListGunung
            imageGunung = itemView.imageGunung
            tvNamaGunung = itemView.tvNamaGunung
            tvLokasiGunung = itemView.tvLokasiGunung
        }
    }

}