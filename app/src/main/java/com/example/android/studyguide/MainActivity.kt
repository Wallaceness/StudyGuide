package com.example.android.studyguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    var navhost: NavHostFragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navhost= supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

//    fun pickRandom(view: View) {
//        val length = allTopics?.size
//        if (length!=null){
//            val item= round((Math.random()*(length-1))).toInt()
//            topic?.text= allTopics?.get(item)?.word
//        }
//    }

    fun navigateTo(id: Int){
        NavHostFragment.findNavController(navhost!!).navigate(id)
    }

}
