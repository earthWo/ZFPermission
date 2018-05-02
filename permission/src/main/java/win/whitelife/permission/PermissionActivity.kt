package win.whitelife.permission

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import win.whitelife.permission.`interface`.IRegisterActivity
import win.whitelife.permission.`interface`.RequestCallback

/**
 * @author wuzefeng
 * 2018/5/2
 */
class PermissionActivity : AppCompatActivity(),IRegisterActivity{

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
    }

    /**
     * 检查权限
     */
    override fun checkPermission() {

        if(permissions!=null&& permissions!!.isNotEmpty()){

            ActivityCompat.requestPermissions(this, permissions!!,PermissionConfig.INTENT_REQUEST)

        }else if(callback!=null){
            callback!!.finish(null)
            finish()
        }else{
            finish()
        }
    }















}