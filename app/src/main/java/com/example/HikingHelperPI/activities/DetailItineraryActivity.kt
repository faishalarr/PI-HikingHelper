package com.example.HikingHelperPI.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.example.HikingHelperPI.R
import com.example.HikingHelperPI.model.ModelItinerary
import kotlinx.android.synthetic.main.activity_detail_itinerary.*

class DetailItineraryActivity : AppCompatActivity() {

    lateinit var modelItinerary: ModelItinerary
    var NamaItinerary: String? = null
    var Deskripsi: String? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_itinerary)
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvDetailItinerary.justificationMode = JUSTIFICATION_MODE_INTER_WORD }

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //get data intent
        modelItinerary = intent.getSerializableExtra(DETAIL_ITINERARY) as ModelItinerary
        if (modelItinerary != null) {
            NamaItinerary = modelItinerary.NamaItinerary
            Deskripsi = modelItinerary.DeskripsiItinerary

            Glide.with(this)
                .load(modelItinerary.ImageItinerary)
                .into(imageItinerary)

            tvNamaItinerary.setText(NamaItinerary)
            tvDetailItinerary.setText(Deskripsi)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val DETAIL_ITINERARY = "DETAIL_ITINERARY"
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