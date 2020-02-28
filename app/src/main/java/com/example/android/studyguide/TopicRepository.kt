package com.example.android.studyguide

import android.app.Application
import androidx.lifecycle.LiveData


class TopicRepository constructor(application: Application) {
    private var mWordDao: TopicDao? = null
    private var mAllWords: LiveData<List<Topic?>?>? = null

    // Note that in order to unit test the WordRepository, you have to remove the Application
// dependency. This adds complexity and much more code, and this sample is not about testing.
// See the BasicSample in the android-architecture-components repository at
// https://github.com/googlesamples
    init{
        val db: TopicRoomDatabase? = TopicRoomDatabase.getDatabase(application!!.applicationContext)
        mWordDao = db?.topicDao()
        mAllWords = mWordDao?.getAlphabetizedWords() as LiveData<List<Topic?>?>?
    }

    // Room executes all queries on a separate thread.
// Observed LiveData will notify the observer when the data has changed.
    fun getAllWords(): LiveData<List<Topic?>?>? {
        return mAllWords
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
// that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(word: Topic?) {
        TopicRoomDatabase.databaseWriteExecutor.execute({ mWordDao?.insert(word) })
    }
}