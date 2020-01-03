package com.example.strawpoll.ui.vote

import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.get
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.strawpoll.R
import com.example.strawpoll.databinding.FragmentVoteBinding
import com.example.strawpoll.ui.list.ListFragmentDirections
import kotlinx.android.synthetic.main.fragment_vote.*

class VoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application

        val binding: FragmentVoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_vote, container, false)

        binding.lifecycleOwner = this

        val selectedStrawpoll = VoteFragmentArgs.fromBundle(arguments!!).selectedStrawpoll

        val viewModelFactory = VoteViewModelFactory(selectedStrawpoll, application)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(VoteViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.answers.observe(this, Observer {
            it.forEach {
                val rb = RadioButton(this.context)
                rb.text = it.answer
                rb.id = it.id
                binding.answerGroup.addView(rb)
            }
        })

        binding.answerGroup.setOnCheckedChangeListener { _, _ ->
            binding.voteButton.isEnabled = true
        }

        binding.voteButton.setOnClickListener { view: View ->
            viewModel.vote(answerGroup.checkedRadioButtonId)
            view.findNavController().navigate(
                VoteFragmentDirections.actionVoteFragmentToResultFragment(
                    viewModel.selectedStrawpoll.value!!
                )
            )
        }
        return binding.root
    }
}