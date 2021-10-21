package com.task.instabug.ui.errors

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.task.instabug.R
import kotlinx.android.synthetic.main.error_dialog_layout.*

class ErrorHandler(private val context: Context) : ErrorHandling {

    override fun handleError() {

        val dialog = Dialog(context, R.style.DialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.error_dialog_layout)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.tv_error.text = context.getString(R.string.UnExpectedError)
        dialog.btn_cancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}