package com.haris.weitani.moviecataloguejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.haris.weitani.moviecataloguejetpack.common.AppExecutors
import com.haris.weitani.moviecataloguejetpack.data.remote.ApiResponse
import com.haris.weitani.moviecataloguejetpack.vo.Resource
import com.haris.weitani.moviecataloguejetpack.vo.StatusResponse

abstract class NetworkBoundResource<ResultType, RequestType>(appExecutors: AppExecutors?) {

    private val result: MediatorLiveData<Resource<ResultType?>?>? = MediatorLiveData<Resource<ResultType?>?>()
    private val mExecutors: AppExecutors? = appExecutors
    protected fun onFetchFailed() {}
    protected abstract fun loadFromDB(): LiveData<ResultType?>?
    protected abstract fun shouldFetch(data: ResultType?): Boolean?
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType?>?>?
    protected abstract fun saveCallResult(data: RequestType?)

    init {
        result!!.setValue(Resource.loading(null))
        val dbSource = loadFromDB()
        result.addSource(dbSource!!) { data: ResultType? ->
            result.removeSource(dbSource)
            if (shouldFetch(data)!!) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(
                    dbSource
                ) { newData: ResultType? ->
                    result.setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType?>?) {
        val apiResponse: LiveData<ApiResponse<RequestType?>?>? = createCall()
        result!!.addSource(
            dbSource!!
        ) { newData: ResultType? ->
            result.setValue(Resource.loading(newData))
        }
        result.addSource<ApiResponse<RequestType?>?>(
            apiResponse!!
        ) { response: ApiResponse<RequestType?>? ->
            result.removeSource<ApiResponse<RequestType?>?>(apiResponse)
            result.removeSource(dbSource)
            when (response?.status) {
                StatusResponse.SUCCESS -> mExecutors?.diskIO()?.execute {
                    saveCallResult(response.body)
                    mExecutors?.mainThread()?.execute {
                        result.addSource(
                            loadFromDB()!!
                        ) { newData: ResultType? ->
                            result.setValue(
                                Resource.success(newData)
                            )
                        }
                    }
                }
                StatusResponse.EMPTY -> mExecutors?.mainThread()?.execute {
                    result.addSource(
                        loadFromDB()!!
                    ) { newData: ResultType? ->
                        result.setValue(
                            Resource.success(newData)
                        )
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(
                        dbSource
                    ) { newData: ResultType? ->
                        result.setValue(Resource.error(response.message, newData))
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType?>?>? {
        return result
    }
}