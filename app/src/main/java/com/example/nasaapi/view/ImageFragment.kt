package com.example.nasaapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nasaapi.databinding.FragmentImageBinding
import com.example.nasaapi.viewmodel.ImageViewModel
import com.squareup.picasso.Picasso

class ImageFragment : Fragment() {
    private lateinit var binding: FragmentImageBinding
    private lateinit var viewModel: ImageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[ImageViewModel::class.java]
        binding.apply {
            toolBarImage.tag = "Close"
            imgImage.setOnClickListener {
                if (toolBarImage.tag.equals("Open")) {
                    toolBarImage.tag = "Close"
                    toolBarImage.visibility = View.GONE
                } else {
                    toolBarImage.tag = "Open"
                    toolBarImage.visibility = View.VISIBLE
                }
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        val bundle = this.arguments
        if (bundle != null) {
            val text = bundle.getString("search")
            if (text != null) {
                viewModel.getData(text.toString())
                Toast.makeText(activity, text.toString(), Toast.LENGTH_SHORT).show()
                viewModel.getCollection().observe(viewLifecycleOwner, Observer {
                    Picasso.get().load(it.url).into(binding.imgImage)
                })
            }
        }
        return binding.root
    }

}