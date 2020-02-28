package com.example.android.studyguide

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TopicDao {
    // allowing the insert of the same word multiple times by passing a
// conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Topic?)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query(
        "SELECT * from word_table ORDER BY word ASC"
    )
    fun getAlphabetizedWords(): LiveData<List<Topic?>?>?
}
