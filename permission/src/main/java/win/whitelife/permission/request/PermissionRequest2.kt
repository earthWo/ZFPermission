package win.whitelife.permission.request

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import win.whitelife.permission.PermissionActivity
import win.whitelife.permission.PermissionChecker
import win.whitelife.permission.PermissionDialogFragment
import win.whitelife.permission.RegisterCallback
import win.whitelife.permission.interfaces.Action
import win.whitelife.permission.interfaces.RequestAction
import java.util.*

/**
 * @author wuzefeng
 * 2018/5/4
 */
class PermissionRequest2: Request {

    private var actionFinish: Action?=null

    private var actionDenied: Action?=null

    private var actionGranted: Action?=null

    private var context : Context?=null

    private var permissionList : LinkedList<String>?=null

    constructor(context: Context){
        this.context=context
        permissionList=LinkedList()
    }


    private fun finish(permissions: Array<String>?){
        if(actionFinish!=null){
            actionFinish!!.action(permissions)
        }
    }

    private fun denied(permissions: Array<String>?){
        if(actionDenied!=null){
            actionDenied!!.action(permissions)
        }
    }

    private fun granted(permissions: Array<String>?){
        if(actionGranted!=null){
            actionGranted!!.action(permissions)
        }
    }



    override fun start() {
        if(permissionList!=null&&permissionList!!.isNotEmpty()){
            val array=checkPermission()
            if(array.isNotEmpty()){
                startRequestPermission(array)
            }else{
                finish(null)
            }
        }else {
            finish(null)
        }
    }

    /**
     * start request
     */
    private fun startRequestPermission(array: Array<String>){

        val grantedArray =ArrayList<String>()

        val deniedArray =ArrayList<String>()

        for(i in 0 until array.size){
            when{
            //通过
                PermissionChecker.checkPermission(context!!,array[i])->{
                    grantedArray.add(array[i])
                }
                else->{
                    deniedArray.add(array[i])
                }
            }
        }

        if(deniedArray.isNotEmpty()){

            val fragment= PermissionDialogFragment()
                    .setCancel(object : RequestAction {
                        override fun action() {
                            granted(grantedArray.toTypedArray())
                            denied(deniedArray.toTypedArray())
                            finish(array)
                        }
                    })
                    .setFinish(object : RequestAction {
                        override fun action() {
                            requestFinish(array)
                        }
                    })
            fragment.show((context as Activity).fragmentManager,"PermissionDialog")


        }else{
            granted(grantedArray.toTypedArray())
            denied(deniedArray.toTypedArray())
            finish(array)
        }

    }

    private fun requestFinish(permissions: Array<String>){
        val deniedList: LinkedList<String>?=LinkedList()
        val grantedList: LinkedList<String>?=LinkedList()
        for ( permission in permissions!!.iterator() ){
            //已经有权限
            if(!PermissionChecker.checkPermission(context!!,permission!!)){
                //权限被拒绝
                deniedList!!.add(permission)
            }else{
                grantedList!!.add(permission)
            }
        }
        granted(grantedList!!.toTypedArray())
        denied(deniedList!!.toTypedArray())
        finish(permissions)
    }

    /**
     * check denied permissions
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
        return list!!.toTypedArray()
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

    override fun setFinishAction(action: Action): Request {
        this.actionFinish=action
        return this
    }

    override fun setGrantedAction(action: Action): Request {
        this.actionDenied=action
        return this
    }

    override fun setDeniedAction(action: Action): Request {
        this.actionGranted=action
        return this
    }

}