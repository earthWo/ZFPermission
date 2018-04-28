package win.whitelife.permission.request

import win.whitelife.permission.callback.RequestCallback

/**
 * wuzefeng
 * 2018/4/28
 */
interface Request {

    /**
     * start request
     */
    fun start()

    /**
     * add request permission
     */
    fun addPermission(permission :String) :Request

    /**
     * add request permission
     */
    fun addPermission(vararg permissions: String) :Request


    /**
     * add request permission
     */
    fun addPermission(permissions: List<String>) :Request

    /**
     * add callback
     */
    fun addCallback(callback: RequestCallback)


}