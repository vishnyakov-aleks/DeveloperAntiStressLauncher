package com.any.developerantistresslauncher

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions






class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        call_if_scared_btn.setOnClickListener {
            tryCall()
        }

        val startMain = Intent(Intent.ACTION_MAIN)
                .apply {
                    addCategory(Intent.CATEGORY_HOME)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
        startActivity(startMain)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(CALL_PHONE_RC)
    private fun tryCall() {
        val perms = arrayOf(Manifest.permission.CALL_PHONE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            val callIntent = Intent(Intent.ACTION_CALL).apply { data = Uri.parse("tel:+79267589641") }
            startActivity(callIntent)
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_for_call_permission),
                    CALL_PHONE_RC, *perms)
        }
    }


    companion object {
        const val CALL_PHONE_RC = 41343
    }
}
