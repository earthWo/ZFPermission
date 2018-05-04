package win.whitelife.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker

/**
 * @author wuzefeng
 * 2018/5/3
 */
class PermissionChecker {


    companion object {

        /**
         * 判断是否有权限
         */
        fun checkPermission(context: Context,permission: String):Boolean{
            //已经有权限
            return PermissionChecker.checkSelfPermission(context,permission)== PackageManager.PERMISSION_GRANTED
        }


        /**
         * 是否选择了不再显示
         */
        fun isRequestAlwaysDenied(context: Context,permission: String):Boolean{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context is Activity ) {
                return !context.shouldShowRequestPermissionRationale(permission)
            }
            return false
        }
    }
}