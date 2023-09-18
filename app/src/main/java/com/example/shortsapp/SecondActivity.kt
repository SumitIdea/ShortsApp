package com.example.shortsapp

import com.example.shortsapp.adapter.VideoAdapter
import com.example.shortsapp.model.VideoModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SecondActivity : AppCompatActivity()/*, VideoAdapter.ClickListener*/ {

    lateinit var adapter: VideoAdapter
    lateinit var viewPager2:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        /**set fullscreen*/
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        /**set find id*/
        viewPager2 = findViewById(R.id.v_pager)

        /**set database*/
        val mDataBase = Firebase.database.getReference("videos")

        val options = FirebaseRecyclerOptions.Builder<VideoModel>()
            .setQuery(mDataBase,VideoModel::class.java)
            .build()
        /**set adapter*/
        adapter = VideoAdapter(options/*,this*/)
        viewPager2.adapter = adapter

    }


    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }/**set adapter*/

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

//    override fun onClick(position: Int) {
//        Log.d("Position",position.toString())
//    }
//
//    override fun onItemClick(string: String) {
//    Log.d("OnItemClick",string)
//    }
//
//    override fun onItemLongClick(position: Int, v: View?) {
//        TODO("Not yet implemented")
//    }


}