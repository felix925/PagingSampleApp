package com.example.pagingsampleapp.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnimeSearchResult (
    @SerializedName("works")
    val animeList: List<Anime>,
    val total_count: Int,
    val next_page: Int,
    val prev_page: Int?
)

@Entity(tableName = "anime")
data class Anime(
    @PrimaryKey(autoGenerate = true)
    val sortId: Int = 0,
    val id: Int,
    val sessionId: String = "",
    val isLiked: Boolean = false,
    val episodes_count: Int,
    val images: Images,
    val mal_anime_id: String,
    val media: String,
    val media_text: String,
    val official_site_url: String,
    val released_on: String,
    val released_on_about: String,
    val season_name: String?,
    val season_name_text: String?,
    val syobocal_tid: String,
    val title: String,
    val title_kana: String,
    val twitter_hashtag: String,
    val twitter_username: String,
    val watchers_count: Int,
    val wikipedia_url: String
): Serializable {
    data class Facebook(
        val og_image_url: String?
    ): Serializable

    data class Images(
        val facebook: Facebook,
        val recommended_url: String,
        val twitter: Twitter
    ): Serializable

    data class Twitter(
        val bigger_avatar_url: String?,
        val image_url: String?,
        val mini_avatar_url: String?,
        val normal_avatar_url: String?,
        val original_avatar_url: String?
    ): Serializable
}