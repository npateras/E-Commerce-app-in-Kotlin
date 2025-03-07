package com.unipi.mpsp21043.admin.utils

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.behavior.SwipeDismissBehavior.SWIPE_DIRECTION_ANY
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.textfield.TextInputLayout
import com.unipi.mpsp21043.admin.R

fun snackBarSuccessClass(view: View, message: String) =
    SnackBarSuccessClass
        .make(view, message)
        .setBehavior(BaseTransientBottomBar.Behavior().apply { setSwipeDirection(SWIPE_DIRECTION_ANY) })
        .show()

fun snackBarSuccessLargeClass(view: View, message: String) =
    SnackBarSuccessLargeClass
        .make(view, message)
        .setBehavior(BaseTransientBottomBar.Behavior().apply { setSwipeDirection(SWIPE_DIRECTION_ANY) })
        .show()

fun snackBarErrorClass(view: View, message: String) =
    SnackBarErrorClass
        .make(view, message)
        .setBehavior(BaseTransientBottomBar.Behavior().apply { setSwipeDirection(SWIPE_DIRECTION_ANY) })
        .show()

fun snackBarErrorClass(view: View, message: String, anchor: View) =
    SnackBarErrorClass
        .make(view, message)
        .setBehavior(BaseTransientBottomBar.Behavior().apply { setSwipeDirection(SWIPE_DIRECTION_ANY) })
        .setAnchorView(anchor)
        .show()

fun textInputLayoutError(view: TextInputLayout, message: String) =
    view.apply {
        requestFocus()
        error = message
        background = AppCompatResources.getDrawable(this.context, R.drawable.text_input_background_error)
    }

fun textInputLayoutNormal(view: TextInputLayout) =
    view.apply {
        isErrorEnabled = false
        background = AppCompatResources.getDrawable(this.context, R.drawable.text_input_background)
    }

class Helper {}
