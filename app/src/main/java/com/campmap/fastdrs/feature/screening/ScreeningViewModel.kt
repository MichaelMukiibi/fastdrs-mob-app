package com.campmap.fastdrs.feature.screening

import androidx.lifecycle.ViewModel
import com.campmap.fastdrs.core.image.FundusImage
import com.campmap.fastdrs.domain.model.Eye
import com.campmap.fastdrs.domain.model.Screening
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class ScreeningViewModel : ViewModel() {
    private val _screeningState = MutableStateFlow<Screening?>(null)
    val screeningState: StateFlow<Screening?> = _screeningState

    fun startScreening(eye: Eye) {
        _screeningState.value = Screening(
            id = UUID.randomUUID().toString(),
            timestamp = System.currentTimeMillis(),
            eye = eye,
            image = null
        )
    }

    fun updateImage(image: FundusImage) {
        _screeningState.value = _screeningState.value?.copy(image = image)
    }

    fun setPrediction(prediction: Any) {
        _screeningState.value = _screeningState.value?.copy(prediction = prediction)
    }
}
