package win.whitelife.permission

import android.content.Context
import win.whitelife.permission.request.PermissionRequest
import win.whitelife.permission.request.Request

/**
 * @author wuzefeng
 * 2018/4/28
 */
class PermissionManager {


    companion object {


        fun with(context:Context):Request{
            return PermissionRequest(context)
        }





    }


}