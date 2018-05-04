package win.whitelife.permission

import android.content.Context
import android.os.Build
import win.whitelife.permission.request.PermissionRequest
import win.whitelife.permission.request.PermissionRequest2
import win.whitelife.permission.request.Request

/**
 * @author wuzefeng
 * 2018/4/28
 */
class PermissionManager {


    companion object {

        fun with(context:Context):Request{
            return if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                PermissionRequest(context)
            }else{
                PermissionRequest2(context)
            }
        }
    }

}