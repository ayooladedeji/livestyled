package com.livestyledtask.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import com.livestyledtask.R

/**
 * Created by Ayo on 18/07/2017.
 */

object SimpleDialog {

    fun show(context: Context, title: String?, message: String?, cancelable: Boolean) {
        val builder = AlertDialog.Builder(context)

        if (message != null)
            builder.setMessage(message)

        if (title != null)
            builder.setTitle(title)

        builder.setCancelable(cancelable)
        builder.setPositiveButton(context.getString(R.string.ok_text)) { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }

}
