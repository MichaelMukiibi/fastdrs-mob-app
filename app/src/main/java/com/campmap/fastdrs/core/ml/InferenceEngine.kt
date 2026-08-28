package com.campmap.fastdrs.core.ml

import com.campmap.fastdrs.core.image.FundusImage

interface InferenceEngine {
    suspend fun predict(image: FundusImage): Prediction
}
