package com.example.android.studyguide

import android.content.Context
import android.content.res.AssetManager
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.*
import java.util.*
import java.util.concurrent.Executors

@Database(entities = [Topic::class], version = 1, exportSchema = false)
abstract class TopicRoomDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao

    var topicsList: ArrayList<String>?= ArrayList<String>()


    companion object {
        @Volatile
        var topicsList: ArrayList<String> = ArrayList<String>()
        private var INSTANCE: TopicRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): TopicRoomDatabase? {
            val asset:InputStream=context.applicationContext.assets.open("Android_Topics.txt")
            val reader: BufferedReader = BufferedReader(InputStreamReader(asset))
            var line=reader.readLine();
            while (line!=null){
                topicsList.add(line)
                line = reader.readLine()
            }
            if (INSTANCE == null) {
                synchronized(TopicRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TopicRoomDatabase::class.java, "word_database"
                        )
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback: Callback =
            object : Callback() {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    // If you want to keep data through app restarts,
// comment out the following block
                    databaseWriteExecutor.execute {
                        // Populate the database in the background.
                        // If you want to start with more words, just add them.
                        val dao: TopicDao = INSTANCE!!.topicDao()
                        dao.deleteAll()
                        for (item in topicsList){
                            dao.insert(Topic(item))
                        }
                    }
                }
            }
    }
}
