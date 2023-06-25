package com.hopcape.findmy.feat_home.domain.utils

import com.hopcape.findmy.feat_home.domain.ItemType
import com.hopcape.findmy.feat_home.domain.Status

fun String.toItemType(): ItemType {
    return when(this){
        "Lost" -> ItemType.LOST
        else -> ItemType.FOUND
    }
}

fun String.toItemStatus(): Status {
    return when(this){
        "Missing" -> Status.MISSING
        else -> Status.FOUND
    }
}