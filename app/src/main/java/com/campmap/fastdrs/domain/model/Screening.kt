package com.campmap.fastdrs.domain.model

import com.campmap.fastdrs.core.image.FundusImage

enum class Eye {
    LEFT,
    RIGHT
}

data class Screening(
    val id: String,
    val timestamp: Long,
    val eye: Eye,
    val image: FundusImage?,
    val prediction: Any? = null
)
