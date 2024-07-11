package com.example.catlist20

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var catAdapter: CatAdapter
    private val catList = mutableListOf<CatModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        catAdapter = CatAdapter(catList) { data ->
            val intent = Intent(this, InfoActivity::class.java).apply {
                putExtra("catName", data.name)
                putExtra("catOrigin", data.origin)
                putExtra("catTemperament", data.temperament)
                putExtra("catColors", data.colors.joinToString(", "))
                putExtra("catDescription", data.description)
                putExtra("catImage", data.image)
            }
            startActivity(intent)
        }
        recyclerView.adapter = catAdapter

        fetchCats()

    }

    private fun fetchCats() {
        val apiService = RetrofitClient.getClient("https://freetestapi.com/api/v1/cats/").create(ApiService::class.java)
        apiService.getCats().enqueue(object : Callback<List<CatModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<CatModel>>, response: Response<List<CatModel>>) {
                if (response.isSuccessful && response.body() != null) {
                    catList.addAll(response.body()!!)
                    catAdapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<CatModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to fetch cats", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

