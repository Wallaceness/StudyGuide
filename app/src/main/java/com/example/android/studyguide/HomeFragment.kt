package com.example.android.studyguide


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlin.math.round

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    var viewModel:MainActivityViewModel? = null
    var allTopics: List<Topic>? = null
    var topic: TextView? = null
    var randomButton:Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView:View =inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = MainActivityViewModel(activity!!.application)
        topic = rootView.findViewById<TextView>(R.id.topicItem)
        randomButton = rootView.findViewById(R.id.randombtn)

        viewModel?.getTopics()?.observe(this, Observer<List<Topic>>{response ->
            allTopics = response
        })

        randomButton!!.setOnClickListener({v->
            val length = allTopics?.size
            if (length!=null){
                val item= round((Math.random()*(length-1))).toInt()
                topic?.text= allTopics?.get(item)?.word
            }
        })

        rootView.findViewById<Button>(R.id.navigateBtn).setOnClickListener({view->
            (activity as MainActivity).navigateTo(R.id.addNewFragment)
        })

        return rootView
    }



}
