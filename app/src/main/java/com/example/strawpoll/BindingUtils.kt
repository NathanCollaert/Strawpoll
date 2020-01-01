package com.example.strawpoll

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.strawpoll.domain.Answer
import com.example.strawpoll.domain.Strawpoll
import com.example.strawpoll.ui.list.StrawpollApiStatus
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

@BindingAdapter("Question")
fun TextView.setQuestion(item: Strawpoll?){
    item?.let{
        text = item.question
    }
}

@BindingAdapter("DateAdded")
fun TextView.setDateCreated(item: Strawpoll?){
    item?.let{
        text = item.dateCreated.format(DateTimeFormatter.ofPattern("'Added on 'dd/MM/yyyy' at 'HH:mm:ss"))
    }
}

@BindingAdapter("TotalVoteAmount")
fun TextView.setTotalVoteAmount(item: Strawpoll?){
    item?.let{
        text = item.answers.stream().collect(Collectors.summingInt(Answer::amountVoted)).toString()
    }
}

@BindingAdapter("strawpollApiStatus")
fun bindStatus(statusImageView: ImageView, status : StrawpollApiStatus?){
    when(status){
        StrawpollApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        StrawpollApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        StrawpollApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}