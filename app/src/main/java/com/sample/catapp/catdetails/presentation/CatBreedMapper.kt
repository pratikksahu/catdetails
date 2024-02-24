package com.sample.catapp.catdetails.presentation

import com.sample.catdetails.CatItem
import com.sample.catdetails.Image
import com.sample.catapp.catdetails.data.network.CatEntity
import com.sample.catapp.catdetails.data.network.Weight

fun CatEntity.mapApiResponseToUiCatData(): CatItem {
    return CatItem(
        weight = getWeight(weight),
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
        image = getImage(image)
    )
}

private fun getWeight(weight: Weight?): com.sample.catdetails.Weight? = weight?.let {
    com.sample.catdetails.Weight(
        imperial = weight.imperial,
        metric = weight.metric
    )
}

private fun getImage(image: com.sample.catapp.catdetails.data.network.Image?): Image? {
    if (image == null) return null
    return Image(
        id = image.id,
        width = image.width,
        height = image.height,
        url = image.url
    )
}
