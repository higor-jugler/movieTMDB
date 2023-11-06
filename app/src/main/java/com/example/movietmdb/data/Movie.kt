package com.example.movietmdb.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(
    foreignKeys = [ForeignKey(
        entity = Movie::class,
        parentColumns = ["id"],
        childColumns = ["imageId"],
        onDelete = ForeignKey.CASCADE
    )]
)
@JsonClass(generateAdapter = true)
class Movie() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    var imageId: Int? = null
    var id: Int? = null
    var title: String? = null
    var overview: String? = null

    @Embedded
    @Json(name = "poster_path")
    var posterPath: String? = null
    @Json(name = "vote_average")
    var voteAverage: Double? = null

    @Ignore
    constructor(
        imageId: Int?,
        id: Int?,
        title: String?,
        overview: String?,
        posterPath: String?,
        voteAverage: Double?
    ) : this() {
        this.imageId = imageId
        this.id = id
        this.title = title
        this.overview = overview
        this.posterPath = posterPath
        this.voteAverage = voteAverage
    }

    fun getIdString(): String {
        return id?.toString() ?: ""
    }

    fun getImageUrl(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }
}
