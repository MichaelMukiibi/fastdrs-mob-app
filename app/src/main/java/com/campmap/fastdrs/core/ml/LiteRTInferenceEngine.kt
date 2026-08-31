package com.campmap.fastdrs.core.ml

import android.content.Context
import com.campmap.fastdrs.core.image.FundusImage
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class LiteRTInferenceEngine(private val context: Context) : InferenceEngine {
    private var interpreter: Interpreter? = null

    init {
        val modelFile = loadModelFile("fastdrs_model.tflite")
        interpreter = Interpreter(modelFile)
    }

    private fun loadModelFile(filename: String): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd(filename)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }

    override suspend fun predict(image: FundusImage): Prediction {
        val preprocessor = ImagePreprocessor()
        val inputBuffer = preprocessor.preprocess(image, context)

        // Model output tensor: Assuming [1, 5] (Batch size 1, 5 classes)
        val output = Array(1) { FloatArray(5) }

        val start = System.currentTimeMillis()
        interpreter?.run(inputBuffer, output)
        val latency = System.currentTimeMillis() - start

        val probabilities = output[0]
        val maxProb = probabilities.maxOrNull() ?: 0f
        val predictedIndex = probabilities.indexOfFirst { it == maxProb }

        return Prediction(
            predictedClass = DRClass.entries[predictedIndex],
            confidence = maxProb,
            probabilities = DRClass.entries.associateWith { probabilities[it.ordinal] },
            inferenceLatencyMs = latency
        )
    }
}
