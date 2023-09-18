package com.example.shortsapp.adapter


import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.shortsapp.MainActivity
import com.example.shortsapp.R
import com.example.shortsapp.model.VideoModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class VideoAdapter(options: FirebaseRecyclerOptions<VideoModel?>/*, val listener: ClickListener*/) :
    FirebaseRecyclerAdapter<VideoModel?, VideoAdapter.MyViewHolder?>(options) {
    var selectedPosition = 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: VideoModel) {

        holder.setdata(model)
//        holder.itemView.setOnClickListener {
//            selectedPosition = position
//            listener.onClick(position)
//            listener.onItemClick("sumit")
//            Log.d("CLickEventOnRecy","Clicked")
//
////                Toast.makeText(context,am.toString(), Toast.LENGTH_LONG).show()
//            notifyDataSetChanged()
//
//        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_video_row, parent, false)
        return MyViewHolder(view)
    }
//    interface ClickListener {
//
//        fun onClick(position: Int)
//        fun onItemClick(string: String)
//        fun onItemLongClick(position: Int, v: View?)
//    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var videoView: VideoView
    var title: TextView
    var desc: TextView
    var pbar: ProgressBar
    var fav: ImageView
    var paus: ImageView
    var isFav = false
    var isPlaying = false
    var mediaPlayer: MediaPlayer? = null

    fun setdata(obj: VideoModel) {
        videoView.setVideoPath(obj.url)
        title.setText(obj.title)
        desc.setText(obj.desc)

        videoView.setOnPreparedListener { mediaPlayer ->
            pbar.visibility = View.GONE
//                mediaPlayer.start()
            this.mediaPlayer = mediaPlayer

        }

        videoView.setOnCompletionListener { mediaPlayer -> mediaPlayer.start() }

        // Add click listener to pause/play button
        paus.setOnClickListener {
            if (isPlaying) {
                mediaPlayer?.pause()
                paus.setImageResource(R.drawable.baseline_play_arrow_24)
            } else {
                mediaPlayer?.start()
                paus.setImageResource(R.drawable.baseline_pause_24)
            }
            isPlaying = !isPlaying
        }

        // Initialize the state of the pause/play button
        if (isPlaying) {
            paus.setImageResource(R.drawable.baseline_pause_24)
        } else {
            paus.setImageResource(R.drawable.baseline_play_arrow_24)
        }


            fav.setOnClickListener {
                if (!isFav){
                    fav.setImageResource(R.drawable.ic_fill_favorite)
                    isFav = true
                }
                else{
                    fav.setImageResource(R.drawable.ic_favorite)
                    isFav = false
                }

            }
        }

        init {
            videoView = itemView.findViewById<View>(R.id.videoView) as VideoView
            title = itemView.findViewById<View>(R.id.textVideoTitle) as TextView
            desc = itemView.findViewById<View>(R.id.textVideoDescription) as TextView
            pbar = itemView.findViewById<View>(R.id.videoProgressBar) as ProgressBar
            fav = itemView.findViewById(R.id.favorites) as ImageView
            paus = itemView.findViewById(R.id.pause) as ImageView
        }
    }
}