package com.campmap.fastdrs.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
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

@Entity
data class Screening(
    @PrimaryKey val id: String,
    val patientId: String,
    val timestamp: Long,
    val eye: Eye,
    val image: FundusImage?,
    val prediction: String? = null,
    val status: ScreeningStatus = ScreeningStatus.CREATED
)
