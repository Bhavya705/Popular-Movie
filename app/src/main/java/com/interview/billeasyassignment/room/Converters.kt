package com.interview.billeasyassignment.room

import androidx.room.TypeConverter
import com.interview.billeasyassignment.model.TopMovieDataClass

class Converters {

    @TypeConverter
    fun fromResult(result: TopMovieDataClass.Result): String{
        return result.toString()
    }

}