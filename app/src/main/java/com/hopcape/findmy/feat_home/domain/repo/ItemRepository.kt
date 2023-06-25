package com.hopcape.findmy.feat_home.domain.repo

import com.hopcape.findmy.feat_home.domain.ReportedItem
import com.hopcape.findmy.core.utils.Result

/**
 * Interface used to fetch items from backend as well as perform
 * other operations on the item*/
interface ItemRepository {

    suspend fun getLostItems(): Result<List<ReportedItem>>

}