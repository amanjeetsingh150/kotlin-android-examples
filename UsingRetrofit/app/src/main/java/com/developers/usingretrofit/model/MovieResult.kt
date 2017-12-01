package com.developers.usingretrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Amanjeet Singh on 29/11/17.
 */
data class MovieResult(@SerializedName("page")
                  @Expose
                  var page: Int? = null,
                  @SerializedName("total_results")
                  @Expose
                  var totalResults: Int? = null,
                  @SerializedName("total_pages")
                  @Expose
                  var totalPages: Int? = null,
                  @SerializedName("results")
                  @Expose
                  var results: List<Result>? = null)