package com.example.android.studyguide

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Topic(@field:ColumnInfo(name = "word") val word: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}