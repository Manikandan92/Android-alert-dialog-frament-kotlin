# Kotlin DialogFragment Example

# Usage

1. Import project into Android Studio
2. Initliazing the DialogFragment fragment for your BaseActivity.kt

BaseActivity.kt

fun showAlertDialog(
        title: String?,
        message: String,
        isCancelNeeded: Boolean,
        positiveButtonClickListener: DialogInterface.OnClickListener?,
        negativeButtonClickListener: DialogInterface.OnClickListener?,
        negativeButtonTitle: String?,
        positiveButtonTitle: String?
    ) {
        try {
            removeExistingDialog()
            val alertDialogFragment = GeneralAlertDialogFragment.newInstance(
                title,
                message,
                isCancelNeeded,
                positiveButtonClickListener,
                negativeButtonClickListener,
                negativeButtonTitle,
                positiveButtonTitle
            )
            alertDialogFragment.show(this.supportFragmentManager, LAConstant.ALERT_DIALOG_TAG)
        } catch (e: Exception) {
            LALog().e(TAG, "Base activity showAlertDialog: Caught exception: " + e.message, e)
        }
    }


    /**
     * Shows alert dialog with message and also handles positive and negative button click events
     *
     * @param message                     The message to be displayed in the alert
     * @param positiveButtonClickListener The listener for positive button click event
     * @param negativeButtonClickListener The listener for negative button click event
     * @param negativeButtonTitle         The title of negative button
     * @param positiveButtonTitle         The title of positive button
     */
    fun showAlertDialog(
        message: String,
        positiveButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonTitle: String,
        positiveButtonTitle: String
    ) {
        showAlertDialog(
            null,
            message,
            true,
            positiveButtonClickListener,
            negativeButtonClickListener,
            negativeButtonTitle,
            positiveButtonTitle
        )
    }

    /**
     * Shows alert dialog with title and message and also handles positive and negative button click events
     *
     * @param title                       The title to be displayed in the alert
     * @param message                     The message to be displayed in the alert
     * @param positiveButtonClickListener The listener for positive button click event
     * @param negativeButtonClickListener The listener for negative button click event
     * @param negativeButtonTitle         The title of negative button
     * @param positiveButtonTitle         The title of positive button
     */
    fun showAlertDialog(
        title: String,
        message: String,
        positiveButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonTitle: String,
        positiveButtonTitle: String
    ) {
        showAlertDialog(
            title,
            message,
            true,
            positiveButtonClickListener,
            negativeButtonClickListener,
            negativeButtonTitle,
            positiveButtonTitle
        )
    }

    
    BaseFragment.kt
    
    /**
     * Method to get base activity.
     *
     * @return Returns activity.
     */
    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }
    
    
    protected fun showAlertDialog(
        title: String?,
        message: String,
        isCancelNeeded: Boolean,
        positiveButtonClickListener: DialogInterface.OnClickListener?,
        negativeButtonClickListener: DialogInterface.OnClickListener?,
        negativeButtonTitle: String?,
        positiveButtonTitle: String?
    ) {
        val baseActivity = getBaseActivity()
        if (baseActivity != null) {
            baseActivity!!.showAlertDialog(
                title,
                message,
                isCancelNeeded,
                positiveButtonClickListener,
                negativeButtonClickListener,
                negativeButtonTitle,
                positiveButtonTitle
            )
        }
    }

    protected fun showAlertDialog(
        message: String,
        positiveButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonClickListener: DialogInterface.OnClickListener
    ) {
        showAlertDialog(null, message, true, positiveButtonClickListener, negativeButtonClickListener, null, null)
    }

    /**
     * Method to show alert dialog with title, message and positive click listener
     *
     * @param title                       title of the alert dialog
     * @param message                     message of the alert dialog
     * @param positiveButtonClickListener click listener of positive click listener
     */
    protected fun showAlertDialog(
        title: String,
        message: String,
        positiveButtonClickListener: DialogInterface.OnClickListener
    ) {
        showAlertDialog(title, message, false, positiveButtonClickListener, null, null, null)
    }

    protected fun showAlertDialog(message: String, positiveButtonClickListener: DialogInterface.OnClickListener) {
        showAlertDialog(null, message, false, positiveButtonClickListener, null, null, null)
    }

    /**
     * Method to show alert dialog with message
     *
     * @param message message to be displayed in alert dialog
     */
    protected fun showAlertDialog(message: String) {
        showAlertDialog(null, message, false, null, null, null, null)
    }
    
    
    We can you used thought application in alert dialog fragment class is common.
    
    
    
