package com.rmxdev.pagosven.data.network

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class QrCodeGenerator @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun generateQrCode(amount: String, userId: String): Bitmap {
        val content = "$amount;$userId"
        val bitMatrix = MultiFormatWriter().encode(
            content, BarcodeFormat.QR_CODE, 200, 200
        )

        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) Color.Black.toArgb() else Color.White.toArgb()
                )
            }
        }

        return bitmap
    }

}