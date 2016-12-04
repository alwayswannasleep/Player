package com.github.alwayswannasleep.player.ui.delegate

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.SparseArray
import java.lang.ref.WeakReference
import java.util.*


class PermissionDelegate(val activity: Activity = Activity()) {

    private val PERMISSIONS_REQUEST_CODE = 101
    private val KEY_NOT_SPECIFIED = -1

    private val activityReference: WeakReference<Activity>
    private val handler: Handler

    private var permissionsKeys: SparseArray<List<String>>
    private val tasks: SparseArray<MutableList<() -> Unit>>

    init {
        this.activityReference = WeakReference(activity)
        this.handler = Handler(Looper.getMainLooper())
        this.tasks = SparseArray<MutableList<() -> Unit>>()
        permissionsKeys = SparseArray<List<String>>()
    }

    fun runWithPermissions(task: () -> Unit, vararg requiredPermissions: String) {
        var tempPermissions = requiredPermissions.asList()
        val permissions = requiredPermissions.asList()

        tempPermissions = tempPermissions.filter { ContextCompat.checkSelfPermission(activityReference.get(), it) != PackageManager.PERMISSION_GRANTED }

        if (tempPermissions.isEmpty()) {
            handler.post(task)
            return
        }

        var key = tryGetKey(permissions)

        if (key == KEY_NOT_SPECIFIED) {
            key = UUID.randomUUID().hashCode()
            permissionsKeys.put(key, permissions)
        }

        var runnables: MutableList<() -> Unit>? = tasks.get(key)

        if (runnables == null) {
            runnables = ArrayList()
        }

        runnables.add(task)
        tasks.put(key, runnables)

        ActivityCompat.requestPermissions(activityReference.get(), requiredPermissions, PERMISSIONS_REQUEST_CODE)
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: List<String>, grantResults: IntArray) {
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return
        }

        if (grantResults.isEmpty()) {
            return
        }

        grantResults
                .filter { it != PackageManager.PERMISSION_GRANTED }
                .forEach { return }

        val key = tryGetKey(permissions)

        if (key == KEY_NOT_SPECIFIED) {
            return
        }

        val runnables = tasks.get(key) ?: return

        for (runnable in runnables) {
            handler.post(runnable)
        }

        permissionsKeys.remove(key)
        tasks.remove(key)
    }

    private fun tryGetKey(permissions: List<String>): Int {
        for (i in 0..permissionsKeys.size() - 1) {
            val tempPermissions = permissionsKeys.valueAt(i)

            val tempList = tempPermissions
            val containsInList = permissions.any { tempList.contains(it) }

            if (containsInList) {
                return permissionsKeys.keyAt(i)
            }
        }

        return KEY_NOT_SPECIFIED
    }
}
