package com.paulrybitskyi.docscanner.utils

import android.content.Context
import com.paulrybitskyi.commons.ktx.arePermissionsDenied
import com.paulrybitskyi.commons.ktx.arePermissionsGranted
import com.paulrybitskyi.commons.ktx.isPermissionDenied
import com.paulrybitskyi.commons.ktx.isPermissionGranted


internal interface PermissionVerifier {

    fun isPermissionGranted(permission: String): Boolean

    fun isPermissionDenied(permission: String): Boolean

    fun arePermissionsGranted(permissions: Set<String>): Boolean

    fun arePermissionsDenied(permissions: Set<String>): Boolean

}


internal class PermissionVerifierImpl(
    private val applicationContext: Context
) : PermissionVerifier {


    override fun isPermissionGranted(permission: String): Boolean {
        return applicationContext.isPermissionGranted(permission)
    }


    override fun isPermissionDenied(permission: String): Boolean {
        return applicationContext.isPermissionDenied(permission)
    }


    override fun arePermissionsGranted(permissions: Set<String>): Boolean {
        return applicationContext.arePermissionsGranted(permissions)
    }


    override fun arePermissionsDenied(permissions: Set<String>): Boolean {
        return applicationContext.arePermissionsDenied(permissions)
    }

}