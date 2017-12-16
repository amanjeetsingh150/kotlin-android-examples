package com.developers.autocompletetextviewwithrx.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Amanjeet Singh on 2/12/17.
 */

data class AutoCompleteResult(
        @SerializedName("predictions") var predictions: List<Prediction> = listOf(),
        @SerializedName("status") var status: String = "" //OK
)

data class Prediction(
        @SerializedName("description") var description: String = "", //Cafetoria Café & Shop, Runeberginkatu, Helsinki, Finland
        @SerializedName("id") var id: String = "", //e5cbe8caeafeb91608839cbd2142b3a6813deea8
        @SerializedName("matched_substrings") var matchedSubstrings: List<MatchedSubstring> = listOf(),
        @SerializedName("place_id") var placeId: String = "", //ChIJQ2gmn8zBjUYRBE7bZ0aNaWU
        @SerializedName("reference") var reference: String = "", //ClRHAAAAkv3sICn03-kNJRGb8MHS8Jr9jHahfPvTVcYgqjDNkhb2Iypku-cLHHm_KqvCXSo1ZXSaj2gmNwQHB01-7gcsenZS5pG0x7wcEMNQz15AsGUSENUE_4uCHCvtMA8s160gnh0aFIN6D4E0HyMmo9ska0tYamujcMfe
        @SerializedName("structured_formatting") var structuredFormatting: StructuredFormatting = StructuredFormatting(),
        @SerializedName("terms") var terms: List<Term> = listOf(),
        @SerializedName("types") var types: List<String> = listOf()
)

data class Term(
        @SerializedName("offset") var offset: Int = 0, //0
        @SerializedName("value") var value: String = "" //Cafetoria Café & Shop
)

data class StructuredFormatting(
        @SerializedName("main_text") var mainText: String = "", //Cafetoria Café & Shop
        @SerializedName("main_text_matched_substrings") var mainTextMatchedSubstrings: List<MainTextMatchedSubstring> = listOf(),
        @SerializedName("secondary_text") var secondaryText: String = "" //Runeberginkatu, Helsinki Finland
)

data class MainTextMatchedSubstring(
        @SerializedName("length") var length: Int = 0, //8
        @SerializedName("offset") var offset: Int = 0 //0
)

data class MatchedSubstring(
        @SerializedName("length") var length: Int = 0, //8
        @SerializedName("offset") var offset: Int = 0 //0
)