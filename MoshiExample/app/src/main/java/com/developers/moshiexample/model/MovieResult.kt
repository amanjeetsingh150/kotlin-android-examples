package com.developers.moshiexample.model
import androidx.annotation.Nullable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonQualifier


/**
 * Created by Amanjeet Singh on 9/2/18.
 */

data class MovieResult(
		@Json(name = "page") var page: Int = 0, //1
		@Json(name = "total_results") var totalResults: Int = 0, //19723
		@Json(name = "total_pages") var totalPages: Int = 0, //987
		@Json(name = "results") var results: List<Result> = listOf()
)

data class Result(
		@Json(name = "vote_count") var voteCount: Int = 0, //6638
		@Json(name = "id") var id: Int = 0, //198663
		@Json(name = "video") var video: Boolean = false, //false
		@Json(name = "vote_average") var voteAverage: Int = 0, //7
		@Json(name = "title") var title: String = "", //The Maze Runner
		@Json(name = "popularity") var popularity: Double = 0.0, //444.4728
		@Json(name = "poster_path") var posterPath: String = "", ///coss7RgL0NH6g4fC2s5atvf3dFO.jpg
		@Json(name = "original_language") var originalLanguage: String = "", //en
		@Json(name = "original_title") var originalTitle: String = "", //The Maze Runner
		@Json(name = "genre_ids") var genreIds: List<Int> = listOf(),
		@Json(name = "backdrop_path") var backdropPath: String = "", ///lkOZcsXcOLZYeJ2YxJd3vSldvU4.jpg
		@Json(name = "adult") var adult: Boolean = false, //false
		@Json(name = "overview") var overview: String = "", //Set in a post-apocalyptic world, young Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow “runners” for a shot at escape.
		@Json(name = "release_date") var releaseDate: String = "" //2014-09-10
)