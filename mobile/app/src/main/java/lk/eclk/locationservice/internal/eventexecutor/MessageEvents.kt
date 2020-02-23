package lk.eclk.locationservice.internal.eventexecutor

import android.content.Intent

interface MessageEvents {
    fun startActivityForResult(intent: Intent?, requestCode: Int)
    fun navigateUp(): Boolean
    fun showSnackBar(s: String)
}