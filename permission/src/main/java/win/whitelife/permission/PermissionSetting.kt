package win.whitelife.permission

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings


/**
 * @author wuzefeng
 * 2018/5/4
 */
class PermissionSetting {


    companion object {

        fun gotoPermissionSetting(context: Context): Boolean {
            val localIntent =  Intent()
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                localIntent.data = Uri.parse("package:${context.packageName}")
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.action = Intent.ACTION_VIEW
                localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
                localIntent.putExtra("com.android.settings.ApplicationPkgName",context.packageName)
            }
            context.startActivity(localIntent)
            return true
        }

    }

}