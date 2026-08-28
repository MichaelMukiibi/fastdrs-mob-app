package com.campmap.fastdrs.feature.screening

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campmap.fastdrs.core.image.FundusImage
import com.campmap.fastdrs.core.ml.InferenceEngine
import com.campmap.fastdrs.domain.model.Eye
import com.campmap.fastdrs.domain.model.Screening
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ScreeningViewModel(private val inferenceEngine: InferenceEngine) : ViewModel() {
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

    fun runAnalysis() {
        val image = _screeningState.value?.image ?: return
        viewModelScope.launch {
            val prediction = inferenceEngine.predict(image)
            _screeningState.value = _screeningState.value?.copy(prediction = prediction)
        }
    }
}
