package com.example.HikingHelperPI.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.HikingHelperPI.R
import kotlinx.android.synthetic.main.activity_bantuan.*


class BantuanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)

        val searchView = findViewById<SearchView>(R.id.searchView)
        val listView = findViewById<ListView>(R.id.list_view)

        val listName = arrayOf("Taman Nasional Gunung Leuser 0617872919 (G. Leuser)",
            "Porter Gunung Leuser Mr Jali 081362291844 (G. Leuser)",
            "Pengelola Jalur via Pura Pasar Agung 081342923020 (G.Agung)",
            "Pak Susiono 08113651015 (G. Argopuro)",
            "@gunungkarang.pandeglang (G. Karang)",
            "@wanawisatapulosari (G. Pulosari)",
            "085974421244 (G.Pulosari)",
            "Taman Nasional Bogani Nanri Wartabone (0434) 22548 (G. Tilongkabila)",
            "Taman Nasional Kerinci Seblat (0748)22250 (G. Kerinci)",
            "kang Hendy 081220269190 (G.Cikuray)",
            "Dede Rohana 082120835884 (G.Cikuray)",
            "Bang Jaka 087717717913 (G. Ciremai)",
            "Kang Kusna 0821156878243 (G. Ciremai)",
            "Pak Usep 081912021180 (G. Gede & Pangrango)",
            "Base Camp Guntur Pondok Pendaki (082115777755)",
            "Kang Zoel 089661003465 (G. Papandayan)",
            "Erfan 085659135058 (G. Papandayan)",
            "@kawahputihecotourism (G. Patuha)",
            "Himpala Rakutak 082116134666",
            "Pak Dadang via Jalur Kawah Ratu  085724995370 (G. Salak)",
            "Kang Achenk via Jalur Cidahu  08176689004 (G. Salak)",
            "@gunungandong (G. Andong)",
            "Jalur Desa Sawit 085848865246 (G. Andong)",
            "Hasan 085729264379 (G. Lawu)",
            "Pak agus 085642072573 (G. Lawu)",
            "Pak Barri 081393062419 (G. Merbabu)",
            "Pak Syamsuri 081329266656 (G. Merapi)",
            "Mas Thipuk Sidarta 085225552130 (G. Merbabu)",
            "Mas Kamplink 085743432595 (G. Merbabu)",
            "Jalur Patakbanteng 085228283428 (G. Prau)",
            "Pak Amin 081227967705 (G. Sindoro)",
            "Pak Sugeng 085726000335 (G. Slamet)",
            "Mas Uceng 085643755398 (G. Slamet)",
            "Jalur Garung 085868611446 (G. Sumbing)",
            "Arief via Jalur Bandungan 081225711243 (G. Ungaran)",
            "Samhaji 082336446256 (G. Argopuro)",
            "Rudi 081330787722 (G. Arjuno)",
            "Junaedi 081554432204 (G. Arjuno)",
            "@tamannasional_baluran (G. Baluran)",
            "bidksda_III@yahoo.co.id (G. Ijen)",
            "Nunung via Jalur Panderman Batu Malang  081555667595 (G. Panderman)",
            "Pos 1 Perijinan Pendakian Gunung Penanggungan Via Tamiajeng 081232152477 (G. Penanggungan)",
            "KPW Gunung Raung (0332) 321305 (G. Raung)",
            "Pak Samsul 0341787055 (G. Semeru)",
            "Taman Nasional Bromo Tengger Semeru (0341) 491-828 (G. Semeru)",
            "Via Jalur Tretes 085856052510 (G. Welirang)",
            "085347403699 (G. Batu Daya)",
            "Jalur Lembah 082134621538 (G. Bawang)",
            "Basecamp 082352917242 (G. Halau Halau)",
            "Taman Nasional Gunung Bukit Baka Bukit Raya 056523521 (G. Bukit Raya)",
            "082269333912 (G. Tanggamus)",
            "balaitnmanusela@gmail.com (G. Binaiya)",
            "Darno Lamane 081356429102 (G. Gamalama)",
            "Mailudu Mugu 085240745199 (G. Gamalama)",
            "Mas Lihun 085367588494 (G.Rinjani)",
            "Dephut : (0370) 27851 (G. Rinjani)",
            "Pengelola Basecamp Arisman 085216032004 (G. Tambora)",
            "Basecamp Desa Pancasila 082340693138 (G. Tambora)",
            "+6296934098 (G. Jayawijaya)",
            "Pengelola Basecamp Tri Karno 081355068840 (G. Latimojong)",
            "Suwarno 081523657993 (G. Lokon)",
            "Fery rusmawan 081342765436 (G. Lokon)",
            "Deni 085214034450 (G. Marapi)",
            "083198280308 (G. Sibayak)",
            "082307690260 (G. Dempo)",
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