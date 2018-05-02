package win.whitelife.permission.request

import android.content.Context
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

    constructor(context: Context){
        this.context=context
        permissionList=LinkedList()
    }


    override fun start() {

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
