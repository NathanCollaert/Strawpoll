package com.example.strawpoll.ui.vote

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.strawpoll.R
import com.example.strawpoll.databinding.FragmentVoteBinding

class VoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentVoteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vote, container, false)
        //binding.pollQuestion.text = VoteFragmentArgs.fromBundle(
        //    arguments!!
        //).question
        //for(i in 0 until VoteFragmentArgs.fromBundle(arguments!!).answers.size){
        //    val rb = RadioButton(this.context)
        //    rb.text = VoteFragmentArgs.fromBundle(arguments!!).answers[i]
        //    rb.id = i
        //    binding.answerGroup.addView(rb)
        //}

        var chosenId = -1
        binding.answerGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { _, i->
                chosenId = i
                binding.voteButton.isClickable = true
            })

        binding.voteButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                VoteFragmentDirections.actionVoteFragmentToResultFragment(
                    chosenId
                )
            )
        }
        return binding.root
    }
}
