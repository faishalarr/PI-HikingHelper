package com.example.HikingHelperPI.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.HikingHelperPI.R
import com.example.HikingHelperPI.adapter.ItineraryAdapter
import com.example.HikingHelperPI.model.ModelItinerary
import kotlinx.android.synthetic.main.fragment_itinerary.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class FragmentItinerary : Fragment() {

    var modelItinerary: MutableList<ModelItinerary> = ArrayList()
    lateinit var itineraryAdapter: ItineraryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_itinerary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        itineraryAdapter = ItineraryAdapter(context, modelItinerary)
        rvItinerary.setLayoutManager(LinearLayoutManager(context))
        rvItinerary.setAdapter(itineraryAdapter)
        rvItinerary.setHasFixedSize(true)

        //get data nama peralatan
        getListItinerary()
    }

    private fun getListItinerary() {
        try {
            val stream = requireContext().assets.open("dataItinerary.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val strContent = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObject = JSONObject(strContent)
                val jsonArray = jsonObject.getJSONArray("itinerary")
                for (i in 0 until jsonArray.length()) {
                    val jsonObjectData = jsonArray.getJSONObject(i)
                    val itinerary = ModelItinerary()
                    itinerary.NamaItinerary = jsonObjectData.getString("nama")
                    itinerary.ImageItinerary = jsonObjectData.getString("image_url")
                    itinerary.TipeItinerary = jsonObjectData.getString("tipe")
                    itinerary.DeskripsiItinerary = jsonObjectData.getString("deskripsi")
                    modelItinerary.add(itinerary)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } catch (ignored: IOException) {
            Toast.makeText(context, "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.", Toast.LENGTH_SHORT).show()
        }
    }

}