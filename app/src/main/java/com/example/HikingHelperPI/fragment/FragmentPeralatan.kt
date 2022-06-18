package com.example.HikingHelperPI.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.HikingHelperPI.R
import com.example.HikingHelperPI.adapter.PeralatanAdapter
import com.example.HikingHelperPI.model.ModelPeralatan
import kotlinx.android.synthetic.main.fragment_peralatan.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class FragmentPeralatan : Fragment() {

    var modelPeralatan: MutableList<ModelPeralatan> = ArrayList()
    lateinit var peralatanAdapter: PeralatanAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_peralatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        peralatanAdapter = PeralatanAdapter(context, modelPeralatan)
        rvPeralatan.setLayoutManager(LinearLayoutManager(context))
        rvPeralatan.setAdapter(peralatanAdapter)
        rvPeralatan.setHasFixedSize(true)

        //get data nama peralatan
        getPeralatanGunung()
    }

    private fun getPeralatanGunung() {
        try {
            val stream = requireContext().assets.open("dataPeralatan.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val strContent = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObject = JSONObject(strContent)
                val jsonArray = jsonObject.getJSONArray("peralatan")
                for (i in 0 until jsonArray.length()) {
                    val jsonObjectData = jsonArray.getJSONObject(i)
                    val peralatan = ModelPeralatan()
                    peralatan.NamaPeralatan = jsonObjectData.getString("nama")
                    peralatan.ImagePeralatan = jsonObjectData.getString("image_url")
                    peralatan.TipePeralatan = jsonObjectData.getString("tipe")
                    peralatan.DeskripsiPeralatan = jsonObjectData.getString("deskripsi")
                    peralatan.TipsPeralatan = jsonObjectData.getString("tips")
                    modelPeralatan.add(peralatan)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } catch (ignored: IOException) {
            Toast.makeText(context, "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.", Toast.LENGTH_SHORT).show()
        }
    }

}