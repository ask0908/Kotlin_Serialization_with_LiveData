package com.example.livedatatest.features.home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class FeedModel(
    val allServices: List<ServiceModel>,
    val popularServices: List<ServiceModel>,
    val posts: List<PostModel>
)

@Parcelize
data class ServiceModel(
    val id: Int,
    val serviceId: Int,
    val name: String,
    val longName: String? = null,
    val imageUrl: String? = null,
    val proCount: Int? = 0
): Parcelable

data class PostModel(
    val title: String,
    val category: String? = null,
    val imageUrl: String? = null,
    val link: String? = null
)