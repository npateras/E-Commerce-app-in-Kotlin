package com.unipi.mpsp21043.admin.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.unipi.mpsp21043.admin.R
import com.unipi.mpsp21043.admin.extension.findSuitableParent

class SnackBarSuccessLargeClass(
    parent: ViewGroup,
    content: SnackBarSuccessLargeView
) : BaseTransientBottomBar<SnackBarSuccessLargeClass>(parent, content, content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)

        getView().findViewById<TextView>(R.id.button_snackbar_success_large_dismiss).setOnClickListener { dismiss() }
        animationMode = ANIMATION_MODE_SLIDE
    }

    companion object {

        fun make(view: View, contentTxt: String): SnackBarSuccessLargeClass {

            // First we find a suitable parent for our custom view
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            // We inflate our custom view
            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.layout_snackbar_success_large,
                parent,
                false
            ) as SnackBarSuccessLargeView

            customView.apply {
                findViewById<TextView>(R.id.text_view_snackbar_success_large_context).text = contentTxt
            }

            // We create and return our Snack-bar
            return SnackBarSuccessLargeClass(
                parent,
                customView
            )
        }
    }

}
