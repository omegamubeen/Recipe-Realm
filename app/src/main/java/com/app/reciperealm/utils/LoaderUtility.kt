package com.app.reciperealm.utils

import androidx.fragment.app.Fragment

object LoaderUtility {

    private var loaderDialog: LoaderDialog? = null

    fun Fragment.showLoader() {
        hideLoader() // hide existing dialog if visible
        loaderDialog = LoaderDialog(requireContext())
        loaderDialog?.show()
    }

    fun hideLoader() {
        loaderDialog?.dismiss()
        loaderDialog = null
    }
}
