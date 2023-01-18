package com.example.livedatatest.features.home.domain.interactor

import com.example.livedatatest.base.BaseResult
import com.example.livedatatest.base.BaseUseCase
import com.example.livedatatest.features.home.domain.HomeRepository
import com.example.livedatatest.features.home.domain.model.FeedModel
import javax.inject.Inject

class GetHomeFeed @Inject constructor(
    private val homeRepository: HomeRepository
): BaseUseCase<FeedModel, Unit>() {
    override suspend fun getData(params: Unit?): BaseResult<FeedModel> =
        homeRepository.getHomeFeed()
}