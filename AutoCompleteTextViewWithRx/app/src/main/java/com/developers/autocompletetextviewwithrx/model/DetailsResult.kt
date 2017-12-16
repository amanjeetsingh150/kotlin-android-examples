package com.developers.autocompletetextviewwithrx.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Amanjeet Singh on 11/12/17.
 */

data class DetailsResult(
		@SerializedName("html_attributions") var htmlAttributions: List<Any> = listOf(),
		@SerializedName("result") var result: Result = Result(),
		@SerializedName("status") var status: String = "" //OK
)

data class Result(
		@SerializedName("address_components") var addressComponents: List<AddressComponent> = listOf(),
		@SerializedName("adr_address") var adrAddress: String = "", //<span class="street-address">Lohjanharjuntie 1147</span>, <span class="postal-code">08680</span> <span class="locality">Lohja</span>, <span class="country-name">Finland</span>
		@SerializedName("formatted_address") var formattedAddress: String = "", //Lohjanharjuntie 1147, 08680 Lohja, Finland
		@SerializedName("formatted_phone_number") var formattedPhoneNumber: String = "", //010 4235570
		@SerializedName("geometry") var geometry: Geometry = Geometry(),
		@SerializedName("icon") var icon: String = "", //https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png
		@SerializedName("id") var id: String = "", //ee494c54587eff3764babc44966978a285c2235b
		@SerializedName("international_phone_number") var internationalPhoneNumber: String = "", //+358 10 4235570
		@SerializedName("name") var name: String = "", //Cafetoria roastery Oy
		@SerializedName("opening_hours") var openingHours: OpeningHours = OpeningHours(),
		@SerializedName("photos") var photos: List<Photo> = listOf(),
		@SerializedName("place_id") var placeId: String = "", //ChIJyXOdUcvBjUYRjg8bPG2Oi3s
		@SerializedName("rating") var rating: Double = 0.0, //4.9
		@SerializedName("reference") var reference: String = "", //CmRRAAAA8Jm87FiUS-7_ASo3SfNi9-y5kfPcBbvfSbgQn3tlKPk9c69Lz86T8HaHYNH6-cCH0Ue5EvmCuBnhyBxAJeClSKo_qfV7uw5pnH949EpAZ3-B65W2NaLjSk7uSll_V6GzEhDy8ctCzPMJtIcXs1QnE200GhR2WUCQwTXKv5MhsqAUR4eQ8_QXQQ
		@SerializedName("reviews") var reviews: List<Review> = listOf(),
		@SerializedName("scope") var scope: String = "", //GOOGLE
		@SerializedName("types") var types: List<String> = listOf(),
		@SerializedName("url") var url: String = "", //https://maps.google.com/?cid=8902365688238903182
		@SerializedName("utc_offset") var utcOffset: Int = 0, //120
		@SerializedName("vicinity") var vicinity: String = "", //Lohjanharjuntie 1147
		@SerializedName("website") var website: String = "" //http://cafetoria.fi/
)

data class OpeningHours(
		@SerializedName("open_now") var openNow: Boolean = false, //false
		@SerializedName("periods") var periods: List<Period> = listOf(),
		@SerializedName("weekday_text") var weekdayText: List<String> = listOf()
)

data class Period(
		@SerializedName("close") var close: Close = Close(),
		@SerializedName("open") var open: Open = Open()
)

data class Open(
		@SerializedName("day") var day: Int = 0, //5
		@SerializedName("time") var time: String = "" //1000
)

data class Close(
		@SerializedName("day") var day: Int = 0, //5
		@SerializedName("time") var time: String = "" //1500
)

data class Photo(
		@SerializedName("height") var height: Int = 0, //1080
		@SerializedName("html_attributions") var htmlAttributions: List<String> = listOf(),
		@SerializedName("photo_reference") var photoReference: String = "", //CmRZAAAAiDPzbhVsBV6Ura44PK1-8RGEYe9fqiDBz7ERi0gL03qVoyrC-2lf5dYDperWYeaBjB2pbSXx9oQTw8-8fcbv6fV5pBcrdX-HJObtb0Y46P8z-QWGXIxxgcORQNFf5KMHEhBMX8FsIvgg1CgVQYo_5NC5GhTryrVYlYI5nLS_vJ_uZKwMX5qZeA
		@SerializedName("width") var width: Int = 0 //1920
)

data class AddressComponent(
		@SerializedName("long_name") var longName: String = "", //1147
		@SerializedName("short_name") var shortName: String = "", //1147
		@SerializedName("types") var types: List<String> = listOf()
)

data class Geometry(
		@SerializedName("location") var location: Location = Location(),
		@SerializedName("viewport") var viewport: Viewport = Viewport()
)

data class Location(
		@SerializedName("lat") var lat: Double = 0.0, //60.2872349
		@SerializedName("lng") var lng: Double = 0.0 //24.1795243
)

data class Viewport(
		@SerializedName("northeast") var northeast: Northeast = Northeast(),
		@SerializedName("southwest") var southwest: Southwest = Southwest()
)

data class Southwest(
		@SerializedName("lat") var lat: Double = 0.0, //60.2860965697085
		@SerializedName("lng") var lng: Double = 0.0 //24.1779255697085
)

data class Northeast(
		@SerializedName("lat") var lat: Double = 0.0, //60.28879453029151
		@SerializedName("lng") var lng: Double = 0.0 //24.18062353029151
)

data class Review(
		@SerializedName("author_name") var authorName: String = "", //Ville Takanen
		@SerializedName("author_url") var authorUrl: String = "", //https://www.google.com/maps/contrib/111155357058782619466/reviews
		@SerializedName("language") var language: String = "", //en
		@SerializedName("profile_photo_url") var profilePhotoUrl: String = "", //https://lh3.googleusercontent.com/-qyt4aX__gAo/AAAAAAAAAAI/AAAAAAAA0Js/3VNUdiXo1Z0/s128-c0x00000000-cc-rp-mo-ba4/photo.jpg
		@SerializedName("rating") var rating: Int = 0, //5
		@SerializedName("relative_time_description") var relativeTimeDescription: String = "", //11 months ago
		@SerializedName("text") var text: String = "", //Only thing that tops the service is their signature roast "Grand Palomar"
		@SerializedName("time") var time: Int = 0 //1483263881
)