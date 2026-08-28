package com.campmap.fastdrs.core.ml

data class Prediction(
    val predictedClass: DRClass,
    val confidence: Float,
    val probabilities: Map<DRClass, Float>,
    val inferenceLatencyMs: Long
)
