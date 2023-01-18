package com.example.livedatatest.features.home.domain.interactor

import com.example.livedatatest.base.BaseMapper
import com.example.livedatatest.features.home.data.response.FeedResponse
import com.example.livedatatest.features.home.domain.model.FeedModel

object FeedResponseMapper: BaseMapper<FeedResponse, FeedModel> {
    override fun map(from: FeedResponse): FeedModel = from.toModel()
}