package com.example.strawpoll.ui.list

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.strawpoll.R
import com.example.strawpoll.databinding.FragmentListBinding
import com.example.strawpoll.persistence.StrawpollDatabase

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = StrawpollDatabase.getInstance(application).pollDao

        val viewModelFactory = ListViewModelFactory(dataSource, application)

        val listViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = listViewModel

        val adapter = PollAdapter(PollListener { pollId ->
            listViewModel.onPollClick(pollId)
        })

        listViewModel.navigateToPoll.observe(this,Observer{poll ->
            poll?.let {
                this.findNavController().navigate(ListFragmentDirections.actionListFragmentToVoteFragment(it))
                listViewModel.onPollClickNavigated()
            }
        })

        binding.pollList.adapter = adapter

        listViewModel.polls.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}