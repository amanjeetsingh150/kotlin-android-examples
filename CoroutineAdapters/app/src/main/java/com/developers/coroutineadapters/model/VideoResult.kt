package com.developers.coroutineadapters.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Amanjeet Singh on 9/2/18.
 */

data class VideoResult(
        @SerializedName("id") var id: Int = 0, //856
        @SerializedName("results") var results: List<TrailerResult> = listOf()
)

data class TrailerResult(
        @SerializedName("id") var id: String = "", //569f54cb9251415e5e009306
        @SerializedName("iso_639_1") var iso6391: String = "", //en
        @SerializedName("iso_3166_1") var iso31661: String = "", //US
        @SerializedName("key") var key: String = "", //kYNqYC_jNAg
        @SerializedName("name") var name: String = "", //Who Framed Roger Rabbit? 25th Anniversary Blu-ray Trailer
        @SerializedName("site") var site: String = "", //YouTube
        @SerializedName("size") var size: Int = 0, //1080
        @SerializedName("type") var type: String = "" //Trailer
)