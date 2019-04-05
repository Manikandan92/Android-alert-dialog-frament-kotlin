package com.android.general.alertdialog.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import com.android.general.alertdialog.R

class GeneralAlertDialogFragment : DialogFragment() {
    private val KEY_TITLE = "title"
    private val KEY_MESSAGE = "message"
    private var positiveButtonClickListener: DialogInterface.OnClickListener? = null
    private var negativeButtonClickListener: DialogInterface.OnClickListener? = null
    private var isNegativeRequired: Boolean = false
    private var positiveButtonTitle: String? = null
    private var negativeButtonTitle: String? = null

    companion object {
        fun newInstance(
            title: String?,
            message: String?,
            isCancelNeeded: Boolean,
            positiveClickListener: DialogInterface.OnClickListener?,
            negativeClickListener: DialogInterface.OnClickListener?,
            negativeButton: String?,
            positiveButton: String?
        ): GeneralAlertDialogFragment {
            val alertDialogFragment = GeneralAlertDialogFragment()
            alertDialogFragment.apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)
                    putString(KEY_MESSAGE, message)
                    positiveButtonTitle = positiveButton
                    negativeButtonTitle = negativeButton
                    isNegativeRequired = isCancelNeeded
                    positiveButtonClickListener = positiveClickListener
                    negativeButtonClickListener = negativeClickListener
                }
            }
            return alertDialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity!!)
        var alertTitle: String? = null
        var alertMessage: String? = null
        arguments?.let {
            if (it.containsKey(KEY_TITLE)) {
                alertTitle = it.getString(KEY_TITLE)
                alertDialogBuilder.setTitle(alertTitle)
            }
            if (it.containsKey(KEY_MESSAGE)) {
                alertMessage = it.getString(KEY_MESSAGE)
                alertDialogBuilder.setTitle(alertMessage)
            }
        }
        alertDialogBuilder.setCancelable(false)
        val builder = alertDialogBuilder.create()
        builder.setButton(
            DialogInterface.BUTTON_POSITIVE,
            if (TextUtils.isEmpty(positiveButtonTitle)) {
                getString(R.string.alert_dialog_ok)
            } else {
                positiveButtonTitle
            },
            positiveButtonClickListener
        )
        if (isNegativeRequired) {
            builder.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                if (TextUtils.isEmpty(negativeButtonTitle)) {
                    getString(R.string.alert_dialog_cancel)
                } else {
                    negativeButtonTitle
                },
                negativeButtonClickListener
            )
        }
        builder.setOnShowListener { dialog ->
            val alertDialog = dialog as AlertDialog
            val btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            val btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        }
        return builder
    }
}