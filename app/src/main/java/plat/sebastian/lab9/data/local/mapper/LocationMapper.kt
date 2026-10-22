package plat.sebastian.lab9.data.local.mapper

import plat.sebastian.lab9.Location
import plat.sebastian.lab9.data.local.entity.LocationEntity

fun LocationEntity.toModel(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}

fun Location.toEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension
    )
}