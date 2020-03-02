package com.example.android.studyguide

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.OutputStreamWriter

public class NewFragmentViewModel constructor(application: Application): AndroidViewModel(application){

    var topicRepository:TopicRepository? = null

    init{
        topicRepository = TopicRepository(application)
    }

    fun insert(topic:Topic){
        topicRepository?.insert(topic)
//        val writeStream: OutputStreamWriter = OutputStreamWriter(FileOutputStream("Android_Topics.txt"), "utf-8")
//        writeStream.write(topic.word)
        //writestream not allowed for assets
    }
}
