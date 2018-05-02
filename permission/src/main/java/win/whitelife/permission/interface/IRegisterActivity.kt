package win.whitelife.permission.`interface`

/**
 * @author wuzefeng
 * 2018/5/2
 */
interface IRegisterActivity {


    fun setCallback(callback: RequestCallback?)

    fun setPermission(permissions: List<String>?)
}