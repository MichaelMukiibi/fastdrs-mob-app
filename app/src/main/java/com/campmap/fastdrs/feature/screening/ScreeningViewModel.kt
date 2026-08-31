package com.campmap.fastdrs.feature.screening

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.campmap.fastdrs.core.image.FundusImage
import com.campmap.fastdrs.core.ml.InferenceEngine
import com.campmap.fastdrs.core.ml.LiteRTInferenceEngine
import com.campmap.fastdrs.domain.model.Eye
import com.campmap.fastdrs.domain.model.Screening
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ScreeningViewModel(private val inferenceEngine: InferenceEngine) : ViewModel() {
    private val _screeningState = MutableStateFlow<Screening?>(null)
    val screeningState: StateFlow<Screening?> = _screeningState

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ScreeningViewModel(LiteRTInferenceEngine(context)) as T
                }
            }
    }

    fun startScreening(patientId: String, eye: Eye) {
        _screeningState.value = Screening(
            id = UUID.randomUUID().toString(),
            patientId = patientId,
            timestamp = System.currentTimeMillis(),
            eye = eye,
            image = null
        )
    }

    fun updateImage(image: FundusImage) {
        _screeningState.value = _screeningState.value?.copy(image = image)
    }

    fun runAnalysis() {
        val screening = _screeningState.value ?: return
        val image = screening.image ?: return
        viewModelScope.launch {
            val prediction = inferenceEngine.predict(image)
            _screeningState.value = _screeningState.value?.copy(prediction = prediction.toString())
        }
    }
}
