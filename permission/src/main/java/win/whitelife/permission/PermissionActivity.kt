package win.whitelife.permission

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import win.whitelife.permission.interfaces.IRegisterActivity
import win.whitelife.permission.interfaces.RequestCallback

/**
 * @author wuzefeng
 * 2018/5/2
 */
class PermissionActivity : Activity(),IRegisterActivity{

    private var callback: RequestCallback?=null

    private var permissions: Array<String>?=null


    override fun setCallback(callback: RequestCallback?) {
        this.callback=callback
    }

    override fun setPermission(permissions: Array<String>?) {
        this.permissions=permissions
    }


    companion object {

        /**
         * 启动PermissionActivity
         */
        fun startPermissionActivity(context: Context){
            val intent = Intent(context,PermissionActivity::class.java)
            context.startActivity(intent)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
    }

    /**
     * 检查权限
     */
    override fun checkPermission() {
        if(permissions!=null&& permissions!!.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissions!!,PermissionConfig.INTENT_REQUEST)
        }else if(callback!=null){
            callback!!.requestFinish(null,null)
            finish()
        }else{
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>?, grantResults: IntArray?) {

        //是否是自己的请求
        if(requestCode==PermissionConfig.INTENT_REQUEST){
            finishRequest(permissions!!,grantResults!!)
        }
        finish()
    }


    private fun finishRequest(permissions: Array<String>?, grantResults: IntArray?){
        if(callback!=null){
            callback!!.requestFinish(permissions!!,grantResults!!)
        }
    }




}