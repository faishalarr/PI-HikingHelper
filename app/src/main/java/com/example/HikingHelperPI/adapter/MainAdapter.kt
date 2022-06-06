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
import com.example.HikingHelperPI.activities.ListGunungActivity
import com.example.HikingHelperPI.model.ModelMain
import kotlinx.android.synthetic.main.list_item_main.view.*
import android.widget.Filter
import android.widget.Filterable
import java.util.ArrayList

/**
 * Created by Azhar Rivaldi on 01-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class MainAdapter(
    private val context: Context,
    private val modelMain: MutableList<ModelMain>) : RecyclerView.Adapter<MainAdapter.ViewHolder>(), Filterable {

    private val modelMainFilterList: List<ModelMain> = ArrayList(modelMain  )

    @ExperimentalStdlibApi
    override fun getFilter(): Filter {
        return modelFilter
    }

    @ExperimentalStdlibApi
    private val modelFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ModelMain> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(modelMainFilterList)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (modelMainFilter in modelMainFilterList) {
                    if (modelMainFilter.strLokasi!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(modelMainFilter)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            modelMain.clear()
            modelMain.addAll(results.values as List<ModelMain>)
            notifyDataSetChanged()
        }
    }

    var imageLokasiDrawable = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelMain[position]

        holder.tvLokasi.text = data.strLokasi

        if (data.strLokasi == "Aceh")
            imageLokasiDrawable = R.drawable.logo_aceh
        if (data.strLokasi == "Bali")
            imageLokasiDrawable = R.drawable.logo_bali
        if (data.strLokasi == "Jambi")
            imageLokasiDrawable = R.drawable.logo_jambi
        if (data.strLokasi == "Jawa Timur")
            imageLokasiDrawable = R.drawable.ic_profile
        else if (data.strLokasi == "Jawa Tengah")
            imageLokasiDrawable = R.drawable.ic_profile
        else if (data.strLokasi == "Jawa Barat")
            imageLokasiDrawable = R.drawable.ic_profile
        else if (data.strLokasi == "Luar Pulau Jawa")
            imageLokasiDrawable = R.drawable.ic_profile

        holder.imageLokasi.setImageResource(imageLokasiDrawable)

        holder.cvListLokasi.setOnClickListener {
            val intent = Intent(context, ListGunungActivity::class.java)
            intent.putExtra(ListGunungActivity.LIST_GUNUNG, modelMain[position])
            if (context != null) {
                context.startActivities(arrayOf(intent))
            }
        }
    }

    override fun getItemCount(): Int {
        return modelMain.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListLokasi: CardView
        var tvLokasi: TextView
        var imageLokasi: ImageView

        init {
            cvListLokasi = itemView.cvListLokasi
            tvLokasi = itemView.tvLokasi
            imageLokasi = itemView.imageLokasi
        }
    }

}