package com.example.strawpoll.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.strawpoll.R
import com.example.strawpoll.databinding.FragmentCreateBinding
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment() {

    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCreateBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_create, container, false)

        binding.createButton.setOnClickListener{view:View ->
            if(binding.pollQuestion.text.toString() != "" && binding.answer1.text.toString() != "" && binding.answer2.text.toString() != ""){
                view.findNavController().navigate(
                    CreateFragmentDirections.actionCreateFragmentToVoteFragment(
                        Long.MIN_VALUE
                    )
                )
            }
        }

        viewModel = ViewModelProviders.of(this).get(CreateViewModel::class.java)

        // Inflate the layout for this fragment
        return binding.root
    }
}
