package win.whitelife.permission

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import win.whitelife.permission.`interface`.IRegisterActivity
import win.whitelife.permission.`interface`.RequestCallback

/**
 * @author wuzefeng
 * 2018/5/2
 */
class RegisterCallback :Application.ActivityLifecycleCallbacks{

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

        if(activity is IRegisterActivity){
            (activity as IRegisterActivity).setCallback(callback)
            (activity as IRegisterActivity).setPermission(list)
            (context!!.applicationContext as Application).unregisterActivityLifecycleCallbacks(this)
        }
    }


    private var callback: RequestCallback?=null

    private var list: Array<String>?=null

    private var context: Context?=null

    constructor(context: Context,callback: RequestCallback,list: Array<String>){
        (context.applicationContext as Application).registerActivityLifecycleCallbacks(this)
        this.context=context
        this.callback=callback
        this.list=list
    }



}