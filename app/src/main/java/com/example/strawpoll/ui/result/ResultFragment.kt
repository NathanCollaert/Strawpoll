package com.example.strawpoll.ui.result


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.strawpoll.R
import com.example.strawpoll.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentResultBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        binding.answerChosen.text = ResultFragmentArgs.fromBundle(
            arguments!!
        ).answerChosenId.toString()
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.result_share_menu, menu)
        if(null == getShareIntent().resolveActivity(activity!!.packageManager)){
            menu.findItem(R.id.share)?.setVisible(false)
        }
    }

    private fun getShareIntent(): Intent {
        return ShareCompat.IntentBuilder.from(activity)
            .setText(getString(
                R.string.share_success_text, ResultFragmentArgs.fromBundle(
                    arguments!!
                ).answerChosenId.toString()))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}