package com.example.sih_app_demo.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sih_app_demo.R

class ChatAdapter(private val chatList: List<ChatItem>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Post Image
        val postImage: ImageView = itemView.findViewById(R.id.post_image)
        // Post Title
        val postTitle: TextView = itemView.findViewById(R.id.post_title)
        // Like Icon
        val likeIcon: ImageView = itemView.findViewById(R.id.like_icon)
        // Comment Icon
        val commentIcon: ImageView = itemView.findViewById(R.id.comment_icon)
        // Share Icon
        val shareIcon: ImageView = itemView.findViewById(R.id.share_icon)
        // Like Text
        val likeText: TextView = itemView.findViewById(R.id.like_text)
        // Comment Text
        val commentText: TextView = itemView.findViewById(R.id.comment_text)
        // Share Text
        val shareText: TextView = itemView.findViewById(R.id.share_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatItem = chatList[position]
        holder.postImage.setImageResource(chatItem.postImageResId)
        holder.postTitle.text = chatItem.postTitle
        holder.likeText.text = chatItem.likeCount
        holder.commentText.text = chatItem.commentCount
        holder.shareText.text = chatItem.shareCount
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}
