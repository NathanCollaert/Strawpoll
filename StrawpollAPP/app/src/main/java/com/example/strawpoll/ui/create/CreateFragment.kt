package com.example.strawpoll.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.strawpoll.R
import com.example.strawpoll.databinding.FragmentCreateBinding
import com.example.strawpoll.ui.vote.VoteFragmentDirections

class CreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCreateBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create, container, false
        )

        val application = requireNotNull(this.activity).application

        val viewModelFactory = CreateViewModelFactory(application)

        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CreateViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.strawpoll.observe(this, Observer {
            if(it != null){
                view!!.findNavController().navigate(
                    CreateFragmentDirections.actionCreateFragmentToVoteFragment(
                        it
                    )
                )
                viewModel.onNavigated()
            }
        })

        //val adapter = CreateAdapter()

        //binding.answerList.adapter = adapter

        //viewModel.answers.observe(viewLifecycleOwner, Observer {
        //    it?.let {
        //        adapter.submitList(it)
        //    }
        //})

        return binding.root
    }
}
