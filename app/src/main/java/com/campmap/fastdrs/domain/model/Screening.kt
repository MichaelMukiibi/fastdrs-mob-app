package com.campmap.fastdrs.domain.model

import com.campmap.fastdrs.core.image.FundusImage

enum class Eye {
    LEFT,
    RIGHT
}

enum class ScreeningStatus {
    CREATED,
    IMAGE_SELECTED,
    ANALYZING,
    COMPLETED,
    FAILED
}

data class Screening(
    val id: String,
    val patientId: String,
    val timestamp: Long,
    val eye: Eye,
    val image: FundusImage?,
    val prediction: Any? = null,
    val status: ScreeningStatus = ScreeningStatus.CREATED
)
