package com.developers.coroutineadapters.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Amanjeet Singh on 9/2/18.
 */

data class MovieResult(
		@SerializedName("page") var page: Int = 0, //1
		@SerializedName("total_results") var totalResults: Int = 0, //19637
		@SerializedName("total_pages") var totalPages: Int = 0, //982
		@SerializedName("results") var results: List<Result> = listOf()
)

data class Result(
		@SerializedName("vote_count") var voteCount: Int = 0, //6638
		@SerializedName("id") var id: Int = 0, //198663
		@SerializedName("video") var video: Boolean = false, //false
		@SerializedName("vote_average") var voteAverage: Double = 0.0, //7
		@SerializedName("title") var title: String = "", //The Maze Runner
		@SerializedName("popularity") var popularity: Double = 0.0, //445.890202
		@SerializedName("poster_path") var posterPath: String = "", ///coss7RgL0NH6g4fC2s5atvf3dFO.jpg
		@SerializedName("original_language") var originalLanguage: String = "", //en
		@SerializedName("original_title") var originalTitle: String = "", //The Maze Runner
		@SerializedName("genre_ids") var genreIds: List<Int> = listOf(),
		@SerializedName("backdrop_path") var backdropPath: String = "", ///lkOZcsXcOLZYeJ2YxJd3vSldvU4.jpg
		@SerializedName("adult") var adult: Boolean = false, //false
		@SerializedName("overview") var overview: String = "", //Set in a post-apocalyptic world, young Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow “runners” for a shot at escape.
		@SerializedName("release_date") var releaseDate: String = "" //2014-09-10
)