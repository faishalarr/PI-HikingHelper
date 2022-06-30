package com.example.HikingHelperPI.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import com.example.HikingHelperPI.R
import kotlinx.android.synthetic.main.activity_bantuan.*
import kotlinx.android.synthetic.main.activity_main.*

class BantuanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)

        val searchView = findViewById<SearchView>(R.id.searchView)
        val listView = findViewById<ListView>(R.id.list_view)

        val listName = arrayOf("Pak Susiono 08113651015 (G. Argopuro)",
            "Samhaji 082336446256 (G. Argopuro)",
            "kang Hendy 081220269190 (G.Cikuray)",
            "Dede Rohana 082120835884 (G.Cikuray)",
            "Taman Nasional Gunung Leuser 0617872919 (G. Leuser)",
            "Mas grandong 085713121271 (G. Merapi)",
            "Mas Lihun 085367588494 (G.Rinjani)",
            "Rudi 081330787722 (G. Arjuna)",
            "Taman Nasional Gunung Bukit Baka Bukit Raya 056523521 (G. Bukit Raya)",
            "Hasan 085729264379 (G. Lawu)",
            "Pak agus 085642072573 (G. Lawu)",
            "Pak Barri 081393062419 (G. Merbabu)",
            "Erfan 085659135058 (G. Papandayan)",
            "Kang Zoel 089661003465 (G. Papandayan)",
            "Basecamp Kalilembu 085225152221 (G. Prau)")



        val arrayAdapter = ArrayAdapter(this,   android.R.layout.simple_list_item_1, listName)
        listView.adapter = arrayAdapter

        //back home
        toolbar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(listName.contains(query)){
                    arrayAdapter.filter.filter(query)
                }else{
                    Toast.makeText(applicationContext,"Data Kontak Tidak Ditemukan",Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                arrayAdapter.filter.filter(newText)
                return false
            }

        })
    }
}