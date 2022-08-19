package com.example.nasaapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasaapi.databinding.FragmentSearchBinding
import com.example.nasaapi.db.HistoryDataBase
import com.example.nasaapi.db.HistoryRepo
import com.example.nasaapi.model.SearchItem
import com.example.nasaapi.viewmodel.SearchViewModel


class SearchFragment : Fragment(), SearchHistoryAdapter.HandleItemClick {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var myAdapter: SearchHistoryAdapter
    private lateinit var viewModel: SearchViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        myAdapter = SearchHistoryAdapter(this)
        val dao = HistoryDataBase.getInstance(requireActivity()).historyDAO()
        val repository = HistoryRepo(dao)
        val factory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
        binding.apply {
            edtCustomSearch.imgSearch.setOnClickListener {
                val search = edtCustomSearch.edtSearch.text.toString()
                val history = SearchItem(0, search)
                viewModel.addHistory(history)
                edtCustomSearch.edtSearch.text = null
                getData()
            }
        }
        setUpRecyclerView()
        getData()
        return binding.root
    }

    private fun getData() {
        viewModel.getAllData()
        viewModel.getHistory().observe(viewLifecycleOwner, Observer {
            myAdapter.setData(it)
        })
    }

    private fun setUpRecyclerView() {
        binding.apply {
            searchRecyclerView.layoutManager = LinearLayoutManager(activity)
            searchRecyclerView.adapter = myAdapter
        }
    }

    override fun deleteItem(search: SearchItem) {
        viewModel.deleteHistory(search)
        getData()
    }

    override fun onClickItem(search: SearchItem) {
        val text = search.text
        val action = SearchFragmentDirections.actionSearchFragmentToImageFragment(text)
        findNavController().navigate(action)
    }


}