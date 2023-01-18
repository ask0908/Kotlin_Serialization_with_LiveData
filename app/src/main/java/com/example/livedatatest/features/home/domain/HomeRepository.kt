package com.example.livedatatest.features.home.domain

import com.example.livedatatest.base.BaseResult
import com.example.livedatatest.features.home.domain.model.FeedModel

interface HomeRepository {
    suspend fun getHomeFeed(): BaseResult<FeedModel>
}