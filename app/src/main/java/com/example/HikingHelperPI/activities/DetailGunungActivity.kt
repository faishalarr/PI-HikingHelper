package com.example.HikingHelperPI.activities

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.HikingHelperPI.R
import com.example.HikingHelperPI.model.ModelGunung
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_gunung.*

class DetailGunungActivity : AppCompatActivity(), OnMapReadyCallback {

    var Latitude = 0.0
    var Longitude = 0.0
    var NamaGunung: String? = null
    var Deskripsi: String? = null
    var JalurGunung: String? = null
    var InfoGunung: String? = null
    lateinit var LokasiGunung: String
    lateinit var modelGunung: ModelGunung
    lateinit var gMaps: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_gunung)

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

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        //get data intent
        modelGunung = intent.getSerializableExtra(DETAIL_GUNUNG) as ModelGunung
        if (modelGunung != null) {
            LokasiGunung = modelGunung.LokasiGunung.toString()
            NamaGunung = modelGunung.NamaGunung
            Deskripsi = modelGunung.Deskripsi
            JalurGunung = modelGunung.JalurPendakian
            InfoGunung = modelGunung.InfoGunung
            Latitude = modelGunung.Lat
            Longitude = modelGunung.Long

            Glide.with(this)
                .load(modelGunung.ImageGunung)
                .into(imageGunung)

            tvNamaGunung.setText(NamaGunung)
            tvLokasiGunung.setText(LokasiGunung)
            detDeskripsi.setText(Deskripsi)
            detJalurGunung.setText(JalurGunung)
            detInfoGunung.setText(InfoGunung)
        }
    }

    //set map
    override fun onMapReady(googleMap: GoogleMap) {
        gMaps = googleMap
        val latLng = LatLng(Latitude, Longitude)
        gMaps.addMarker(MarkerOptions().position(latLng).title(NamaGunung))
        gMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        gMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
        gMaps.uiSettings.setAllGesturesEnabled(true)
        gMaps.uiSettings.isZoomGesturesEnabled = true
        gMaps.isTrafficEnabled = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val DETAIL_GUNUNG = "DETAIL_GUNUNG"
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