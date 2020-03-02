package com.example.android.studyguide


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

/**
 * A simple [Fragment] subclass.
 */
class AddNewFragment : Fragment() {

    var save: Button? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView:View = inflater.inflate(R.layout.fragment_add_new, container, false)
        save = rootView.findViewById(R.id.SaveBtn)

        val newFragmentViewModel = NewFragmentViewModel(activity!!.application )

        save?.setOnClickListener({view->
            val topic= rootView.findViewById<EditText>(R.id.newTopicName).text
            newFragmentViewModel.insert(Topic(topic.toString()))
            (activity as MainActivity).navigateTo(R.id.homeFragment)
        })

        return rootView
    }


}
