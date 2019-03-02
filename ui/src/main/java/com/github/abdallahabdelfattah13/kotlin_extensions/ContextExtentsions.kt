package com.github.abdallahabdelfattah13.kotlin_extensions

import android.content.Context
import android.widget.Toast


/**
 * Created by AbdAllah AbdElfattah on 02/03/2019,
 * The D. GmbH,
 * Cairo, Egypt.
 */
fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()