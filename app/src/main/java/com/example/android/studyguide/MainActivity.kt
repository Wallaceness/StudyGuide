package com.example.android.studyguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    var viewModel:MainActivityViewModel? = null
    var allTopics: List<Topic>? = null
    var topic: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = MainActivityViewModel(application)
        topic = findViewById<TextView>(R.id.topicItem)

        viewModel?.getTopics()?.observe(this, Observer<List<Topic>>{response ->
            allTopics = response
        })
    }

    fun pickRandom(view: View) {
        val length = allTopics?.size
        if (length!=null){
            val item= round((Math.random()*(length-1))).toInt()
            topic?.text= allTopics?.get(item)?.word
        }
    }


}
