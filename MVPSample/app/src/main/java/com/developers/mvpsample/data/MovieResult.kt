package com.developers.mvpsample.data
import com.google.gson.annotations.SerializedName


/**
 * Created by Amanjeet Singh on 7/2/18.
 */

data class MovieResult(
		@SerializedName("page") var page: Int = 0, //1
		@SerializedName("total_results") var totalResults: Int = 0, //117
		@SerializedName("total_pages") var totalPages: Int = 0, //6
		@SerializedName("results") var results: List<Result> = listOf()
)

data class Result(
		@SerializedName("vote_count") var voteCount: Int = 0, //7810
		@SerializedName("id") var id: Int = 0, //10195
		@SerializedName("video") var video: Boolean = false, //false
		@SerializedName("vote_average") var voteAverage: Double = 0.0, //6.6
		@SerializedName("title") var title: String = "", //Thor
		@SerializedName("popularity") var popularity: Double = 0.0, //77.972247
		@SerializedName("poster_path") var posterPath: String = "", ///bIuOWTtyFPjsFDevqvF3QrD1aun.jpg
		@SerializedName("original_language") var originalLanguage: String = "", //en
		@SerializedName("original_title") var originalTitle: String = "", //Thor
		@SerializedName("genre_ids") var genreIds: List<Int> = listOf(),
		@SerializedName("backdrop_path") var backdropPath: String = "", ///LvmmDZxkTDqp0DX7mUo621ahdX.jpg
		@SerializedName("adult") var adult: Boolean = false, //false
		@SerializedName("overview") var overview: String = "", //Against his father Odin's will, The Mighty Thor - a powerful but arrogant warrior god - recklessly reignites an ancient war. Thor is cast down to Earth and forced to live among humans as punishment. Once here, Thor learns what it takes to be a true hero when the most dangerous villain of his world sends the darkest forces of Asgard to invade Earth.
		@SerializedName("release_date") var releaseDate: String = "" //2011-04-21
)