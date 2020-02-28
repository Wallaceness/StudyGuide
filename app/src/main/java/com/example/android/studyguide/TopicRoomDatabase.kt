package com.example.android.studyguide

import android.content.Context
import android.content.res.AssetManager
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.File
import java.io.FileNotFoundException
import java.util.*
import java.util.concurrent.Executors

@Database(entities = [Topic::class], version = 1, exportSchema = false)
abstract class TopicRoomDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao


    companion object {
        @Volatile
        private var INSTANCE: TopicRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): TopicRoomDatabase? {
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
//                        val assetManager = AssetManager.
                        var file:File ? =null
                        var scanner:Scanner? = null
                        try{
                            file= File("")//new file to go here.
                            scanner=Scanner(file)
                        }
                        catch(e: FileNotFoundException){
                            e.printStackTrace()
                            return@execute
                        }
                        while (scanner!!.hasNextLine()){
                            val word = Topic(scanner.nextLine())
                            dao.insert(word)
                        }
                    }
                }
            }
    }
}
