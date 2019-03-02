package com.github.abdallahabdelfattah13.kotlin_extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar


/**
 * Created by AbdAllah AbdElfattah on 02/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
fun View.snack(
    message: String,
    length: Int = Snackbar.LENGTH_LONG
) = Snackbar.make(this, message, length).show()