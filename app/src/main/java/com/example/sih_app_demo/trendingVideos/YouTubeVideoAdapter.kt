package com.example.sih_app_demo.trendingVideos

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sih_app_demo.databinding.ItemVideoBinding

class YouTubeVideoAdapter(private val videos: List<YouTubeVideoItem>) :
    RecyclerView.Adapter<YouTubeVideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.bind(video)
    }

    override fun getItemCount(): Int = videos.size

    inner class VideoViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: YouTubeVideoItem) {
            binding.tvVideoTitle.text = video.snippet.title

            // Load the video thumbnail into the VideoView
            val thumbnailUrl = video.snippet.thumbnails.high.url
            Glide.with(binding.root.context)
                .load(thumbnailUrl)
                .into(binding.ivVideoThumbnail)

            // Construct YouTube URL
            val videoUrl = "https://www.youtube.com/watch?v=${video.id.videoId}"

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                itemView.context.startActivity(intent)
            }
        }
    }
}
