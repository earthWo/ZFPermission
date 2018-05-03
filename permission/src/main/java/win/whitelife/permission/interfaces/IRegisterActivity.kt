package win.whitelife.permission.interfaces

/**
 * @author wuzefeng
 * 2018/5/2
 */
interface IRegisterActivity {

    fun setCallback(callback: RequestCallback?)

    fun setPermission(permissions: Array<String>?)

    fun checkPermission()

}