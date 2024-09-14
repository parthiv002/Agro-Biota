package com.example.sih_app_demo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sih_app_demo.R
import com.example.sih_app_demo.databinding.FragmentDashboardBinding
import com.example.sih_app_demo.community.ChatAdapter
import com.example.sih_app_demo.community.ChatItem

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    // Chat-related variables
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var chatList: List<ChatItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize RecyclerView and set LayoutManager
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample chat data
        chatList = listOf(
            ChatItem(
                postImageResId = R.drawable.career,  // Replace with your image resource
                postTitle = "Sample Post 1",
                likeCount = "10",
                commentCount = "5",
                shareCount = "3"
            ),
            ChatItem(
                postImageResId = R.drawable.career,  // Replace with your image resource
                postTitle = "Sample Post 2",
                likeCount = "20",
                commentCount = "15",
                shareCount = "8"
            ),
            ChatItem(
                postImageResId = R.drawable.career,  // Replace with your image resource
                postTitle = "Sample Post 3",
                likeCount = "30",
                commentCount = "25",
                shareCount = "10"
            )
        )

        // Initialize adapter and set it to the RecyclerView
        chatAdapter = ChatAdapter(chatList)
        binding.chatRecyclerView.adapter = chatAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
