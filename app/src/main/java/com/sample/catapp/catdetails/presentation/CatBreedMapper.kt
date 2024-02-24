package com.sample.catapp.catdetails.presentation

import com.sample.catdetails.CatItem
import com.sample.catdetails.Image
import com.sample.catapp.catdetails.data.network.CatEntity
import com.sample.catapp.catdetails.data.network.Weight

fun mapApiResponseToUiCatData(catEntity: CatEntity): CatItem {
    return CatItem(
        weight = getWeight(catEntity.weight),
        id = catEntity.id,
        name = catEntity.name,
        cfaUrl = catEntity.cfaUrl,
        vetstreetUrl = catEntity.vetstreetUrl,
        vcahospitalsUrl = catEntity.vcahospitalsUrl,
        temperament = catEntity.temperament,
        origin = catEntity.origin,
        countryCodes = catEntity.countryCodes,
        countryCode = catEntity.countryCode,
        description = catEntity.description,
        lifeSpan = catEntity.lifeSpan,
        indoor = catEntity.indoor,
        lap = catEntity.lap,
        altNames = catEntity.altNames ?: "",
        adaptability = catEntity.adaptability,
        affectionLevel = catEntity.affectionLevel,
        childFriendly = catEntity.childFriendly,
        dogFriendly = catEntity.dogFriendly,
        energyLevel = catEntity.energyLevel,
        grooming = catEntity.grooming,
        healthIssues = catEntity.healthIssues,
        intelligence = catEntity.intelligence,
        sheddingLevel = catEntity.sheddingLevel,
        socialNeeds = catEntity.socialNeeds,
        strangerFriendly = catEntity.strangerFriendly,
        vocalisation = catEntity.vocalisation,
        experimental = catEntity.experimental,
        hairless = catEntity.hairless,
        natural = catEntity.natural,
        rare = catEntity.rare,
        rex = catEntity.rex,
        suppressedTail = catEntity.suppressedTail,
        shortLegs = catEntity.shortLegs,
        wikipediaUrl = catEntity.wikipediaUrl,
        hypoallergenic = catEntity.hypoallergenic,
        referenceImageId = catEntity.referenceImageId,
        image = getImage(catEntity.image)
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
