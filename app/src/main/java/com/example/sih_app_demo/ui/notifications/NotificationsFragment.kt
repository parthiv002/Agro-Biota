package com.example.sih_app_demo.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sih_app_demo.R
import com.example.sih_app_demo.databinding.FragmentNotificationsBinding
import com.example.sih_app_demo.market.ProductAdapter
import com.example.sih_app_demo.market.ProductItem

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    // Product-related variables
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: List<ProductItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize RecyclerView with GridLayoutManager
        binding.marketRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // 2 columns in the grid

        // Sample product data
        productList = listOf(
            ProductItem(R.drawable.vegetable, "Sample product description 1"),
            ProductItem(R.drawable.vegetable, "Sample product description 2"),
            ProductItem(R.drawable.vegetable, "Sample product description 3"),
            ProductItem(R.drawable.vegetable, "Sample product description 4"),
            ProductItem(R.drawable.vegetable, "Sample product description 5"),
            ProductItem(R.drawable.vegetable, "Sample product description 6"),
            ProductItem(R.drawable.vegetable, "Sample product description 7"),
            ProductItem(R.drawable.vegetable, "Sample product description 8"),
            ProductItem(R.drawable.vegetable, "Sample product description 9")
        )

        // Initialize adapter and set it to the RecyclerView
        productAdapter = ProductAdapter(productList)
        binding.marketRecyclerView.adapter = productAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
