package com.github.alwayswannasleep.player.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.widget.Toast

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.showToast(@StringRes textId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, textId, duration).show()
}

inline fun <reified T : Activity> Context.startActivity(arguments: Bundle? = null) {
    val intent = Intent(this, T::class.java)

    if (arguments != null) {
        intent.putExtras(arguments)
    }

    startActivity(intent)
}
