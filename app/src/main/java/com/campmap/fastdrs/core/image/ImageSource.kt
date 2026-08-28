package com.campmap.fastdrs.core.image

interface ImageSource {
    suspend fun acquire(): FundusImage
}
