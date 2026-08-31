package com.campmap.fastdrs.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
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

class Converters {
    @TypeConverter
    fun fromEye(eye: Eye): String = eye.name
    @TypeConverter
    fun toEye(value: String): Eye = Eye.valueOf(value)

    @TypeConverter
    fun fromStatus(status: ScreeningStatus): String = status.name
    @TypeConverter
    fun toStatus(value: String): ScreeningStatus = ScreeningStatus.valueOf(value)

    @TypeConverter
    fun fromFundusImage(image: FundusImage?): String? = image?.uriString
    @TypeConverter
    fun toFundusImage(value: String?): FundusImage? = value?.let { FundusImage(it) }
}

@Entity
@TypeConverters(Converters::class)
data class Screening(
    @PrimaryKey val id: String,
    val patientId: String,
    val timestamp: Long,
    val eye: Eye,
    val image: FundusImage?,
    val prediction: String? = null,
    val status: ScreeningStatus = ScreeningStatus.CREATED
)
