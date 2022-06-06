package com.example.HikingHelperPI.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import com.example.HikingHelperPI.R
import kotlinx.android.synthetic.main.activity_bantuan.*
import kotlinx.android.synthetic.main.activity_main.*

class BantuanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)

        val searchView = findViewById<SearchView>(R.id.searchView)
        val listView = findViewById<ListView>(R.id.list_view)

        val listName = arrayOf("Arman", "Ansar", "Akash","Sudish")

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
                    Toast.makeText(this@BantuanActivity,"No Match Found", Toast.LENGTH_SHORT).show()
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