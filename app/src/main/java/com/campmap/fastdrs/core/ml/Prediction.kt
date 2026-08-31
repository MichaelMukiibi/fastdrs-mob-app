package com.campmap.fastdrs.core.ml

data class Prediction(
    val predictedClass: DRClass,
    val confidence: Float,
    val probabilities: Map<DRClass, Float>,
    val inferenceLatencyMs: Long
) {
    override fun toString(): String = "$predictedClass (Confidence: $confidence)"
    
    companion object {
        fun fromJson(json: String): Prediction {
            // Simplified for this vertical slice
            return Prediction(DRClass.NO_DR, 0.0f, emptyMap(), 0L)
        }
    }
}
