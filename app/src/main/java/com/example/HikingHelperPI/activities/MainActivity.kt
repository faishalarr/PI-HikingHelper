package com.example.HikingHelperPI.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.HikingHelperPI.R
import com.example.HikingHelperPI.adapter.MainAdapter
import com.example.HikingHelperPI.model.ModelMain
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*
import com.example.HikingHelperPI.activities.*
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    var modelMain: MutableList<ModelMain> = ArrayList()
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //image slider
        val daftargmbr = ArrayList<SlideModel>()

        daftargmbr.add(SlideModel("https://images.bisnis-cdn.com/posts/2019/08/12/1135593/semeru21.jpg","Gunung Semeru"))
        daftargmbr.add(SlideModel("https://mmc.tirto.id/image/2021/05/06/antarafoto-aktivitas-gunung-merapi-060521-afa-4_ratio-16x9.jpg","Gunung Merapi"))
        daftargmbr.add(SlideModel("https://ksmtour.com/media/images/articles/puncak_jaya__sudirman_range__papua__indonesia.jpg","Gunung Jaya Wijaya"))

        val imgSlider = findViewById<ImageSlider>(R.id.imgslide)
        imgSlider.setImageList(daftargmbr)

        //set transparent statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        //transparent background searchview


        //set data adapter to recyclerview
        mainAdapter = MainAdapter(this, modelMain)
        rvLokasi.setLayoutManager(LinearLayoutManager(this))
        rvLokasi.setAdapter(mainAdapter)
        rvLokasi.setHasFixedSize(true)

        //open activity peralatan
        fabPeralatan.setOnClickListener {
            val intent = Intent(this@MainActivity, TipsActivity::class.java)
            startActivity(intent)
        }

        //method get data nama gunung
        getLokasiGunung()
    }

    private fun getLokasiGunung() {
        try {
            val stream = assets.open("dataGunung.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val strContent = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObject = JSONObject(strContent)
                val jsonArray = jsonObject.getJSONArray("gunung")
                for (i in 0 until jsonArray.length()) {
                    val gunung = ModelMain()
                    val jsonObjectData = jsonArray.getJSONObject(i)
                    gunung.Lokasi = jsonObjectData.getString("lokasi")
                    modelMain.add(gunung)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } catch (ignored: IOException) {
            Toast.makeText(this@MainActivity,
                "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}