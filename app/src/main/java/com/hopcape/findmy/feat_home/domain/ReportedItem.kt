package com.hopcape.findmy.feat_home.domain

/**
 * Models the reported or found item*/
data class ReportedItem(
    val status: Status,
    val phone: String,
    val location: String,
    val name: String,
    val imageUrl: String,
    val timestamp: Long,
    val itemId: String = "",
    val itemType: ItemType
)


enum class Status {
    MISSING,
    FOUND,
    CLAIMED
}

enum class ItemType {
    LOST,
    FOUND
}

fun getDummyReportedItems() = listOf(
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
    ReportedItem(Status.MISSING,"+917889534384","Near Janglat Mandi","Wallet","",System.currentTimeMillis(),"",ItemType.LOST),
)
