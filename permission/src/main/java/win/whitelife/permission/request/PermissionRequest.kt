package win.whitelife.permission.request

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import win.whitelife.permission.PermissionActivity
import win.whitelife.permission.PermissionChecker
import win.whitelife.permission.PermissionDialogFragment
import win.whitelife.permission.RegisterCallback
import win.whitelife.permission.interfaces.RequestCallback
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author wuzefeng
 * 2018/5/2
 */
class PermissionRequest : Request,RequestCallback{


    override fun requestFinish(permissions: Array<String>?, grantResults: IntArray?) {

        val grantedArray =ArrayList<String>()

        val deniedArray =ArrayList<String>()

        //是null的情况
        if(permissions==null||grantResults==null){

        }else{

            var hasDeniedPermission=false
            for(i in 0 until permissions.size){

                when{
                    //通过
                    grantResults[i]==  PackageManager.PERMISSION_GRANTED->{
                        grantedArray.add(permissions[i])
                    }
                    //拒绝
                    grantResults[i]==  PackageManager.PERMISSION_DENIED->{
                        //是否被点击了不再显示权限请求，弹出弹框提示用户去打开
                        if(PermissionChecker.isRequestAlwaysDenied(context!!,permissions!![i])){
                            hasDeniedPermission=true
                        }else{
                            deniedArray.add(permissions[i])
                        }
                    }
                }
            }

            if(hasDeniedPermission){
                val fragment=PermissionDialogFragment()
                fragment.show((context as Activity).fragmentManager,"PermissionDialog")
            }
        }
    }


    private var context :Context?=null

    private var permissionList :LinkedList<String>?=null


    constructor(context: Context){
        this.context=context
        permissionList=LinkedList()
    }


    override fun start() {
        if(permissionList!=null&&permissionList!!.isNotEmpty()){
            val array=checkPermission()

            if(array.isNotEmpty()){
                startRequestPermission(array)
            }else{
                this.requestFinish(null,null)
            }
        }else {
           this.requestFinish(null,null)
        }
    }


    /**
     * 开始请求
     */
    private fun startRequestPermission(array: Array<String>){

        //注册回调
        RegisterCallback.registerCallback(context!!,this,array)

        //启动页面
        PermissionActivity.startPermissionActivity(context!!)
    }

    /**
     *
     */
    private fun checkPermission(): Array<String>{

        val list: LinkedList<String>?=LinkedList()

        for ( permission in permissionList!!.iterator() ){

            //权限不为空
            if(!TextUtils.isEmpty(permission)){

                //已经有权限
                if(!PermissionChecker.checkPermission(context!!,permission!!)){
                    //权限被拒绝
                    list!!.add(permission)
                }
            }
        }
        return list!!.toArray(arrayOfNulls<String>(list.size))
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
        return this

    }

}
