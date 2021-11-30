package com.example.pagingsampleapp.data.converter

import androidx.room.TypeConverter
import com.example.pagingsampleapp.data.vo.Anime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AnimeConverter {
    @TypeConverter
    @JvmStatic
    fun facebookToString(data: Anime.Facebook?): String? {
        return data?.let{ Gson().toJson(it) }
    }

    @TypeConverter
    @JvmStatic
    fun stringToFacebook(data: String?): Anime.Facebook? {
        if (data == null) return null
        val dataType = object : TypeToken<Anime.Facebook>() {}.type
        return Gson().fromJson(data, dataType)
    }

    @TypeConverter
    @JvmStatic
    fun imagesToString(data: Anime.Images?): String? {
        return data?.let{ Gson().toJson(it) }
    }

    @TypeConverter
    @JvmStatic
    fun stringToImages(data: String?): Anime.Images? {
        if (data == null) return null
        val dataType = object : TypeToken<Anime.Images>() {}.type
        return Gson().fromJson(data, dataType)
    }

    @TypeConverter
    @JvmStatic
    fun twitterToString(data: Anime.Twitter?): String? {
        return data?.let{ Gson().toJson(it) }
    }

    @TypeConverter
    @JvmStatic
    fun stringToTwitter(data: String?): Anime.Twitter? {
        if (data == null) return null
        val dataType = object : TypeToken<Anime.Twitter>() {}.type
        return Gson().fromJson(data, dataType)
    }
}