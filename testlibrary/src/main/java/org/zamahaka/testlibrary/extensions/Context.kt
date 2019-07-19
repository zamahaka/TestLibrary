package org.zamahaka.testlibrary.extensions

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.getColorCompat(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)

fun Context.getDrawableCompat(
    @DrawableRes resId: Int
): Drawable = ContextCompat.getDrawable(this, resId)
    ?: throw IllegalArgumentException("Cant find drawable for id: ${resId.toString(16)}")


fun Context.hideKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        view.windowToken,
        0
    )
}

fun Context.showKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
        view, InputMethodManager.SHOW_IMPLICIT
    )
}


fun Context.copyToClipBoard(label: CharSequence, value: CharSequence) {
    val clipData = ClipData.newPlainText(label, value)
    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(clipData)
}


fun Context.isPermissionGranted(permission: String) = ContextCompat.checkSelfPermission(
    this, permission
) == PackageManager.PERMISSION_GRANTED

fun Context.permissionNotGranted(permission: String) = !isPermissionGranted(permission)


fun Context.isWriteExternalPermissionGranted() = ContextCompat.checkSelfPermission(
    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
) == PackageManager.PERMISSION_GRANTED

const val WRITE_EXTERNAL_PERMISSION = 1
