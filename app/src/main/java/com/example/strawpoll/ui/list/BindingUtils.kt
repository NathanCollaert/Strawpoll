package com.example.strawpoll.ui.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.strawpoll.persistence.entities.Answer
import com.example.strawpoll.persistence.entities.Poll
import java.util.stream.Collectors

@BindingAdapter("Question")
fun TextView.setQuestion(item:Poll?){
    item?.let{
        text = item.question
    }
}

@BindingAdapter("DateAdded")
fun TextView.setDateAdded(item:Poll?){
    item?.let{
        text = item.creationDate.toString()
    }
}

@BindingAdapter("AmountVoted")
fun TextView.setAmountVoted(item:Poll?){
    item?.let{
        //text = item.answers.stream().collect(Collectors.summingInt(Answer::voteAmount)).toString()
    }
}