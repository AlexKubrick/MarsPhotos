package com.example.android.marsphotos.overview

import com.example.android.marsphotos.network.MarsPhoto

sealed class MarsApiStatus {
    object MarsApiLoading : MarsApiStatus()
    class MarsApiReady(val photos: List<MarsPhoto>): MarsApiStatus()
    object MarsApiError : MarsApiStatus()
}