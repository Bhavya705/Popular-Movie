package com.interview.billeasyassignment.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
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
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pbHome.visibility = View.VISIBLE

        checkInternet()

        btn_retry.setOnClickListener {
            pbHome.visibility = View.VISIBLE
            checkInternet()
        }
        //viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
//        viewModel.readAllData.observe(viewLifecycleOwner){ response ->
//            when(response){
//                is Resource.Error<*> -> {
//                    response.message?.let {
//                        Timber.e("Error in room Response")
//                    }
//                }
//                is Resource.Success<*> -> {
//                    response.data?.let {
//                        Toast.makeText(requireContext(),"Save data",Toast.LENGTH_SHORT).show()
//                    }
//                }
//                is Resource.Loading<*> -> {
//                    response.data?.let {
//                        Toast.makeText(requireContext(),"Loading",Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
    }

    private fun checkInternet() {
        if (isInternetAvailable(requireContext())) {
            hideErrorLayout()
            observeLiveData()
        }else{
            showErrorLayout()
        }
    }

    private fun hideErrorLayout() {
        error_layout.visibility = View.GONE
    }
    private fun showErrorLayout() {
        error_layout.visibility = View.VISIBLE
        pbHome.visibility = View.GONE
    }

    private fun observeLiveData() {
        viewModel.getTopMovieList(APP_ID).observe(viewLifecycleOwner, Observer { topMovieList ->
            pbHome.visibility = View.GONE
            setUpRecyclerView(topMovieList.results)
        })
    }

     fun setUpRecyclerView(imagesList: List<TopMovieDataClass.Result>) {
        val homeAdapter = HomeAdapter(imagesList, requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        rvHome.layoutManager = LinearLayoutManager(context)
        rvHome.adapter = homeAdapter
        rvHome.addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }

        return result
    }

}