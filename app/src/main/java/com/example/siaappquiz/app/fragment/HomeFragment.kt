package com.example.siaappquiz.app.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digisapplication.Network.Coroutines
import com.example.digisapplication.Network.MyApi
import com.example.digisapplication.Network.utils.add
import com.example.digisapplication.Network.utils.toast
import com.example.siaappquiz.app.repo.MostPopulerRepo
import com.example.siaappquiz.R
import com.example.siaappquiz.app.adapter.MostPopulerAdapter
import com.example.siaappquiz.app.models.ResultResp
import com.example.siaappquiz.app.viewmodel.HomeViewModel
import com.example.siaappquiz.databinding.FragmentHomeBinding
import com.example.siaappquiz.network.viewmodefac.MostPopularFactory

class HomeFragment : Fragment() {

    var list: ArrayList<ResultResp> = ArrayList()

    lateinit var binding:FragmentHomeBinding
    private val NYT_ResultAdapter by lazy {
        MostPopulerAdapter(context!!, list)
    }


    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        homeViewModel =  ViewModelProvider(
                this,
            MostPopularFactory(MostPopulerRepo(MyApi()))
        ).get(HomeViewModel::class.java)

        homeViewModel.period = 7

        val lm= LinearLayoutManager(context)
        lm.orientation= RecyclerView.VERTICAL
        binding.recylerNyt.layoutManager =lm
        binding.recylerNyt.adapter = NYT_ResultAdapter

        binding.progressBar.add()


        Coroutines.main {
            val response = homeViewModel.dataMostPopular.await()

            response?.observe(this, Observer {
                if (!it.status.isNullOrEmpty()) {
                    list.clear()
                    list.addAll(it.results)
                    NYT_ResultAdapter.notifyDataSetChanged()
                    binding.progressBar.hide()
                    binding.recylerNyt.add()
                } else {
                    toast(getString(R.string.fail))
                    binding.progressBar.hide()
                }
            })
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}