package com.example.sih_app_demo.ui.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sih_app_demo.DiseaseDiagnosisActivity
import com.example.sih_app_demo.R
import com.example.sih_app_demo.databinding.FragmentHomeBinding
import com.example.sih_app_demo.trendingVideos.YouTubeApiService
import com.example.sih_app_demo.trendingVideos.YouTubeResponse
import com.example.sih_app_demo.trendingVideos.YouTubeVideoAdapter
import com.example.sih_app_demo.trendingVideos.YouTubeVideoItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_CAMERA_PERMISSION = 2
    private lateinit var photoUri: Uri

    private val apiKey = "AIzaSyDqoPuL5dTRSEKNjmbT5xCcm5flNmWJrfI"
    private val query = "plant animal disease prevention"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Find the Take a Picture button and set a click listener
        val takePictureButton: Button = binding.btnTakePicture
        takePictureButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent()
            } else {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
            }
        }

        // Set up click listeners for the buttons in DiseaseDiagnosticActivity
//        val askExpertButton: Button = binding.button_ask_expert // Replace with your actual button reference
//        askExpertButton.setOnClickListener {
//            // Handle ask an expert button click
//            Toast.makeText(context, "Ask an expert button clicked!", Toast.LENGTH_SHORT).show()
//        }
//
//        val askCommunityButton: Button = binding.button_ask_in_community // Replace with your actual button reference
//        askCommunityButton.setOnClickListener {
//            // Handle ask in community button click
//            Toast.makeText(context, "Ask in community button clicked!", Toast.LENGTH_SHORT).show()
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchTrendingVideos()
    }

    private fun fetchTrendingVideos() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(YouTubeApiService::class.java)
        val call = service.getTrendingVideos("snippet", query, "video", 5, apiKey)

        call.enqueue(object : Callback<YouTubeResponse> {
            override fun onResponse(call: Call<YouTubeResponse>, response: Response<YouTubeResponse>) {
                if (response.isSuccessful) {
                    val videos = response.body()?.items ?: emptyList()
                    setupRecyclerView(videos)
                } else {
                    Toast.makeText(context, "Failed to fetch videos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<YouTubeResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView(videos: List<YouTubeVideoItem>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = YouTubeVideoAdapter(videos)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Assume you are collecting the images in a list
            val imageBitmap = data?.extras?.get("data") as? Uri
            // Store the images in a list for the carousel
            val imageList = ArrayList<Int>() // Collect your image resource IDs here

            // Navigate to disease diagnostic page after image confirmation
            val intent = Intent(requireContext(), DiseaseDiagnosisActivity::class.java)
            intent.putIntegerArrayListExtra("imageList", imageList)
            startActivity(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
