package com.interview.billeasyassignment.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.interview.billeasyassignment.R
import com.interview.billeasyassignment.adapter.HomeAdapter
import com.interview.billeasyassignment.model.TopMovieDataClass
import com.interview.billeasyassignment.others.Constants.APP_ID
import com.interview.billeasyassignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Response
import timber.log.Timber

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pbHome.visibility = View.VISIBLE
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.getTopMovieList(APP_ID).observe(viewLifecycleOwner, Observer { topMovieList ->
            pbHome.visibility = View.GONE
            setUpRecyclerView(topMovieList.results)
        })
    }

    private fun setUpRecyclerView(imagesList: List<TopMovieDataClass.Result>) {
        val homeAdapter = HomeAdapter(imagesList, requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        rvHome.layoutManager = LinearLayoutManager(context)
        rvHome.adapter = homeAdapter
        rvHome.addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))
    }
}