package com.relayapp.live.presentation.base

import com.relayapp.live.data.base.DataModel

interface ModelMapper<R : DataModel, T : ViewDataModel> {
    fun mapperToViewDataModel(dataModel: R): T

    fun mapperToDataModel(viewDataModel: ViewDataModel): DataModel {
        TODO("maybe not implement")
    }
}
