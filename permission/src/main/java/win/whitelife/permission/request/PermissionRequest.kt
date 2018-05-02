package win.whitelife.permission.request

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import win.whitelife.permission.`interface`.RequestCallback
import java.util.*

/**
 * @author wuzefeng
 * 2018/5/2
 */
class PermissionRequest : Request{

    private var context :Context?=null

    private var permissionList :LinkedList<String>?=null

    private var callback: RequestCallback?=null


    private var permissionArray: Array<String>?=null

    constructor(context: Context){
        this.context=context
        permissionList=LinkedList()
    }


    override fun start() {

        if(permissionList!=null&&permissionList!!.isNotEmpty()){
            checkPermission()
        }else if(callback!=null){
            callback!!.finish(null)
        }
    }

    /**
     *
     */
    private fun checkPermission(){

        val list: LinkedList<String>?=LinkedList()

        for ( permission in permissionList!!.iterator() ){

            //权限不为空
            if(!TextUtils.isEmpty(permission)){

                val state=  ContextCompat.checkSelfPermission(context!!,permission)
                //已经有权限
                if(state== PackageManager.PERMISSION_DENIED){
                    //权限被拒绝
                    list!!.add(permission)
                }
            }
        }
        permissionArray= list!!.toArray() as Array<String>?
    }

    override fun addPermission(permission: String): Request {
        permissionList!!.add(permission)
        return this
    }

    override fun addPermission(vararg permissions: String): Request {
        permissionList!!.addAll(permissions)
        return this
    }

    override fun addPermission(permissions: List<String>): Request {
        permissionList!!.addAll(permissions)
        return this
    }

    override fun addCallback(callback: RequestCallback) : Request {
        this.callback=callback
        return this

    }

}
