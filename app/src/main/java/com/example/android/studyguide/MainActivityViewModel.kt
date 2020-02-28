package com.example.android.studyguide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

public class MainActivityViewModel constructor(application: Application): AndroidViewModel(application){

    var topicRepository:TopicRepository? = null
    var topicsList: LiveData<List<Topic>>? = null

    init{
        topicRepository = TopicRepository(application)
        topicsList = topicRepository?.getAllWords() as LiveData<List<Topic>>?
    }

    fun getTopics(): LiveData<List<Topic>>{
        return topicsList!!
    }

    fun insert(topic:Topic){
        topicRepository?.insert(topic)
    }
}