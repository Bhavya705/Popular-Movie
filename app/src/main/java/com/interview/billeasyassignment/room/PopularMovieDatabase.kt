package com.interview.billeasyassignment.room

import android.content.Context
import androidx.room.*


@Database(
    entities = [PopularMovieEntity::class],
    version = 1
)
abstract class PopularMovieDatabase: RoomDatabase() {

    abstract fun popularMovieDao(): PopularMovieDao

    companion object{
        @Volatile
        private var instance: PopularMovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance= it}
        }

         fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PopularMovieDatabase::class.java,
                "save_popularMovieDb.db"
            ).build()
    }
}