package com.github.alwayswannasleep.player.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.alwayswannasleep.player.core.AppComponent
import com.github.alwayswannasleep.player.core.PlayerApplication
import com.github.alwayswannasleep.player.ui.delegate.PermissionDelegate

abstract class GenericActivity : AppCompatActivity(), IView {

    protected lateinit var permissionDelegate: PermissionDelegate
    protected lateinit var projectApplication: PlayerApplication
    private lateinit var component: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        projectApplication = application as PlayerApplication
        permissionDelegate = PermissionDelegate(this)
        component = projectApplication.appComponent

        inject(component)
    }

    override fun runWithPermissions(vararg permissions: String, task: () -> Unit) {
        permissionDelegate.runWithPermissions(task, *permissions)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        permissionDelegate.onRequestPermissionsResult(requestCode = requestCode, permissions = permissions.asList(), grantResults = grantResults)
    }

    abstract fun inject(appComponent: AppComponent)
}