package com.sample.dtomodule.catdetails.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.sample.dtomodule.catdetails.network.CatApiResponse

@Entity(
    tableName = CatEntity.TABLE_NAME, indices = [
        Index(value = arrayOf("id"), unique = true)
    ]
)
data class CatEntity(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("weight") val weight: Weight?,
    @SerializedName("name") val name: String,
    @SerializedName("cfa_url") val cfaUrl: String?,
    @SerializedName("vetstreet_url") val vetstreetUrl: String?,
    @SerializedName("vcahospitals_url") val vcahospitalsUrl: String?,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("country_codes") val countryCodes: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("description") val description: String,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("indoor") val indoor: Int,
    @SerializedName("lap") val lap: Int?,
    @SerializedName("alt_names") val altNames: String?,
    @SerializedName("adaptability") val adaptability: Int,
    @SerializedName("affection_level") val affectionLevel: Int,
    @SerializedName("child_friendly") val childFriendly: Int,
    @SerializedName("dog_friendly") val dogFriendly: Int,
    @SerializedName("energy_level") val energyLevel: Int,
    @SerializedName("grooming") val grooming: Int,
    @SerializedName("health_issues") val healthIssues: Int,
    @SerializedName("intelligence") val intelligence: Int,
    @SerializedName("shedding_level") val sheddingLevel: Int,
    @SerializedName("social_needs") val socialNeeds: Int,
    @SerializedName("stranger_friendly") val strangerFriendly: Int,
    @SerializedName("vocalisation") val vocalisation: Int,
    @SerializedName("experimental") val experimental: Int,
    @SerializedName("hairless") val hairless: Int,
    @SerializedName("natural") val natural: Int,
    @SerializedName("rare") val rare: Int,
    @SerializedName("rex") val rex: Int,
    @SerializedName("suppressed_tail") val suppressedTail: Int,
    @SerializedName("short_legs") val shortLegs: Int,
    @SerializedName("wikipedia_url") val wikipediaUrl: String?,
    @SerializedName("hypoallergenic") val hypoallergenic: Int,
    @SerializedName("reference_image_id") val referenceImageId: String?,
    @SerializedName("image") val image: Image?
) {
    companion object {
        const val TABLE_NAME = "CATS"
    }
}

data class Weight(
    @SerializedName("imperial") val imperial: String,
    @SerializedName("metric") val metric: String
)

data class Image(
    @SerializedName("id") val id: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("url") val url: String
)

class WeightToStringConverter {
    @TypeConverter
    fun fromString(value: String): Weight? {
        return Gson().fromJson(value, Weight::class.java)
    }

    @TypeConverter
    fun toString(value: Weight): String {
        return Gson().toJson(value)
    }
}
class ImageInfoToStringConverter {
    @TypeConverter
    fun fromString(value: String): Image? {
        return Gson().fromJson(value, Image::class.java)
    }

    @TypeConverter
    fun toString(value: Image?): String? {
        return Gson().toJson(value, Image::class.java)
    }
}


fun CatApiResponse.toEntity() = CatEntity(
    id = id,
    weight = weight?.toEntityWeight(),
    name = name,
    cfaUrl = cfaUrl,
    vetstreetUrl = vetstreetUrl,
    vcahospitalsUrl = vcahospitalsUrl,
    temperament = temperament,
    origin = origin,
    countryCodes = countryCodes,
    countryCode = countryCode,
    description = description,
    lifeSpan = lifeSpan,
    indoor = indoor,
    lap = lap,
    altNames = altNames,
    adaptability = adaptability,
    affectionLevel = affectionLevel,
    childFriendly = childFriendly,
    dogFriendly = dogFriendly,
    energyLevel = energyLevel,
    grooming = grooming,
    healthIssues = healthIssues,
    intelligence = intelligence,
    sheddingLevel = sheddingLevel,
    socialNeeds = socialNeeds,
    strangerFriendly = strangerFriendly,
    vocalisation = vocalisation,
    experimental = experimental,
    hairless = hairless,
    natural = natural,
    rare = rare,
    rex = rex,
    suppressedTail = suppressedTail,
    shortLegs = shortLegs,
    wikipediaUrl = wikipediaUrl,
    hypoallergenic = hypoallergenic,
    referenceImageId = referenceImageId,
    image = image?.toEntityImage(),
)

private fun com.sample.dtomodule.catdetails.network.Weight.toEntityWeight() = Weight(
    imperial = imperial, metric = metric
)
private fun com.sample.dtomodule.catdetails.network.Image.toEntityImage() = Image(
    id = id, width = width, height = height, url = url
)