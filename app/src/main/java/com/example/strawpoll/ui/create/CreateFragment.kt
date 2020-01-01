package com.example.strawpoll.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.strawpoll.R
import com.example.strawpoll.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {

    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCreateBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create, container, false
        )

        val viewModel =
            ViewModelProviders.of(this).get(CreateViewModel::class.java)

        binding.lifecycleOwner = this

        return binding.root
    }
}
