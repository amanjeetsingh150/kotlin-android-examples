package com.developers.jacksonkotlinmodule.model
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created by Amanjeet Singh on 10/2/18.
 */

data class MovieResult(
		@JsonProperty("page") var page: Int = 0, //1
		@JsonProperty("total_results") var totalResults: Int = 0, //19788
		@JsonProperty("total_pages") var totalPages: Int = 0, //990
		@JsonProperty("results") var results: List<Result> = listOf()
)

data class Result(
		@JsonProperty("vote_count") var voteCount: Int = 0, //6793
		@JsonProperty("id") var id: Int = 0, //198663
		@JsonProperty("video") var video: Boolean = false, //false
		@JsonProperty("vote_average") var voteAverage: Int = 0, //7
		@JsonProperty("title") var title: String = "", //The Maze Runner
		@JsonProperty("popularity") var popularity: Double = 0.0, //535.445142
		@JsonProperty("poster_path") var posterPath: String = "", ///coss7RgL0NH6g4fC2s5atvf3dFO.jpg
		@JsonProperty("original_language") var originalLanguage: String = "", //en
		@JsonProperty("original_title") var originalTitle: String = "", //The Maze Runner
		@JsonProperty("genre_ids") var genreIds: List<Int> = listOf(),
		@JsonProperty("backdrop_path") var backdropPath: String = "", ///lkOZcsXcOLZYeJ2YxJd3vSldvU4.jpg
		@JsonProperty("adult") var adult: Boolean = false, //false
		@JsonProperty("overview") var overview: String = "", //Set in a post-apocalyptic world, young Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow “runners” for a shot at escape.
		@JsonProperty("release_date") var releaseDate: String = "" //2014-09-10
)