package com.sample.catapp.catdetails.presentation

import com.sample.catdetails.CatItem
import com.sample.catdetails.Image
import com.sample.dtomodule.catdetails.database.CatEntity
import com.sample.dtomodule.catdetails.database.Weight

fun CatEntity.toUiCatData(): CatItem {
    return CatItem(
        weight = weight?.toUiCatData(),
        id = id,
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
        altNames = altNames ?: "",
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
        image = image?.toUiCatData()
    )
}
private fun Weight.toUiCatData() = com.sample.catdetails.Weight(
    imperial = imperial,
    metric = metric
)
private fun com.sample.dtomodule.catdetails.database.Image.toUiCatData() = Image(
    id = id,
    width = width,
    height = height,
    url = url
)
