package com.developers.coroutineadapters.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Amanjeet Singh on 9/2/18.
 */

data class CrewResult(
		@SerializedName("id") var id: Int = 0, //856
		@SerializedName("cast") var cast: List<Cast> = listOf(),
		@SerializedName("crew") var crew: List<Crew> = listOf()
)

data class Crew(
		@SerializedName("credit_id") var creditId: String = "", //52fe4282c3a36847f80249a9
		@SerializedName("department") var department: String = "", //Directing
		@SerializedName("gender") var gender: Int = 0, //2
		@SerializedName("id") var id: Int = 0, //24
		@SerializedName("job") var job: String = "", //Director
		@SerializedName("name") var name: String = "", //Robert Zemeckis
		@SerializedName("profile_path") var profilePath: String = "" ///isCuZ9PWIOyXzdf3ihodXzjIumL.jpg
)

data class Cast(
		@SerializedName("cast_id") var castId: Int = 0, //17
		@SerializedName("character") var character: String = "", //Eddie Valiant
		@SerializedName("credit_id") var creditId: String = "", //52fe4283c3a36847f8024a07
		@SerializedName("gender") var gender: Int = 0, //2
		@SerializedName("id") var id: Int = 0, //382
		@SerializedName("name") var name: String = "", //Bob Hoskins
		@SerializedName("order") var order: Int = 0, //0
		@SerializedName("profile_path") var profilePath: String = "" ///mIgAC6q5HcHHxZUIiCOvE6mHLGs.jpg
)