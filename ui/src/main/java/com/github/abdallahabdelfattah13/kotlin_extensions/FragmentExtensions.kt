package com.github.abdallahabdelfattah13.kotlin_extensions

import androidx.fragment.app.Fragment


/**
 * Created by AbdAllah AbdElfattah on 02/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
fun Fragment.toast(message: String) =
    android.widget.Toast.makeText(this.context, message, android.widget.Toast.LENGTH_LONG).show()