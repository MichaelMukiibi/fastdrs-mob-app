package com.campmap.fastdrs.core.ml

import android.content.Context
import android.graphics.Bitmap
import com.campmap.fastdrs.core.image.FundusImage
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ImagePreprocessor(private val inputSize: Int = 224) {

    fun preprocess(image: FundusImage, context: Context): ByteBuffer {
        val bitmap = image.toBitmap(context) ?: throw IllegalArgumentException("Failed to load bitmap")
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, inputSize, inputSize, true)

        val byteBuffer = ByteBuffer.allocateDirect(4 * inputSize * inputSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(inputSize * inputSize)
        scaledBitmap.getPixels(intValues, 0, scaledBitmap.width, 0, 0, scaledBitmap.width, scaledBitmap.height)

        for (pixelValue in intValues) {
            val r = (pixelValue shr 16 and 0xFF)
            val g = (pixelValue shr 8 and 0xFF)
            val b = (pixelValue and 0xFF)

            // Basic normalization (0.0 to 1.0)
            byteBuffer.putFloat((r / 255.0f))
            byteBuffer.putFloat((g / 255.0f))
            byteBuffer.putFloat((b / 255.0f))
        }
        return byteBuffer
    }
}
