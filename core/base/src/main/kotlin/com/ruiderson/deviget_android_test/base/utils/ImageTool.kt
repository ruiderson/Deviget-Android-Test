package com.ruiderson.deviget_android_test.base.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileNotFoundException

class ImageTool(
    private val context: Context
) {

    @SuppressLint("InlinedApi")
    fun saveBitmap(bitmap: Bitmap): Boolean = try {

        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val dirDest = File(Environment.DIRECTORY_PICTURES, CHILD_DIR)
        val date = System.currentTimeMillis()

        val newImage = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$date.$IMAGE_EXTENSION")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/$IMAGE_EXTENSION")
            put(MediaStore.MediaColumns.DATE_ADDED, date)
            put(MediaStore.MediaColumns.DATE_MODIFIED, date)
            put(MediaStore.MediaColumns.SIZE, bitmap.byteCount)
            put(MediaStore.MediaColumns.WIDTH, bitmap.width)
            put(MediaStore.MediaColumns.HEIGHT, bitmap.height)
            put(MediaStore.MediaColumns.RELATIVE_PATH, "$dirDest${File.separator}")
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        context.contentResolver.insert(collection, newImage)?.let { newImageUri ->
            context.contentResolver.openOutputStream(newImageUri, "w").use {
                bitmap.compress(IMAGE_FORMAT, 100, it)
            }

            newImage.clear()
            newImage.put(MediaStore.Images.Media.IS_PENDING, 0)
            context.contentResolver.update(newImageUri, newImage, null, null)

            true
        } ?: false
    } catch (e: Exception) {
        false
    } catch (e: FileNotFoundException) {
        false
    }

    companion object {
        private const val CHILD_DIR = "saved_images"
        private val IMAGE_FORMAT = Bitmap.CompressFormat.JPEG
        private const val IMAGE_EXTENSION = "jpg"
    }
}
