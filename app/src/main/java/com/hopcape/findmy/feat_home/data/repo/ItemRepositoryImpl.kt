package com.hopcape.findmy.feat_home.data.repo

import com.google.firebase.firestore.core.Filter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hopcape.findmy.core.domain.utils.SafeApiCallHandler
import com.hopcape.findmy.core.utils.Result
import com.hopcape.findmy.feat_home.domain.ItemType
import com.hopcape.findmy.feat_home.domain.ReportedItem
import com.hopcape.findmy.feat_home.domain.Status
import com.hopcape.findmy.feat_home.domain.repo.ItemRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val safeApiCallHandler: SafeApiCallHandler
): ItemRepository{
    private val firestore by lazy {
        Firebase.firestore
    }

    override suspend fun getLostItems(): Result<List<ReportedItem>> {
        return safeApiCallHandler.invoke {
            val items = firestore.collection("items").whereEqualTo("visible",true).get().await()
            val reportedItems = items.documents.map { document ->
                ReportedItem(
                    status = Status.MISSING,
                    phone = document.getString("phone") ?: "",
                    location = document.getString("location") ?: "",
                    name = document.getString("name") ?: "",
                    imageUrl = document.getString("image_url") ?: "",
                    timestamp = document.getLong("timestamp") ?: 0L,
                    itemId = document.getString("id") ?: "",
                    itemType = ItemType.LOST
                )
            }
            reportedItems
        }
    }
}