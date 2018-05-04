package win.whitelife.permission

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import win.whitelife.permission.interfaces.RequestAction



/**
 * @author wuzefeng
 * 2018/5/3
 */
class PermissionDialogFragment: DialogFragment(),DialogInterface.OnClickListener{

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
        //click setting
            -1 -> {
                startRequest=true
                setMShowing(this,true)
                PermissionSetting.gotoPermissionSetting(activity)
            }
        //click cancel
            -2 -> {
                if(cancelAction!=null){
                    cancelAction?.action()
                }
            }
        }
    }

    private fun setMShowing(fragment: DialogFragment, mShowing: Boolean) {
        try {
            val field = fragment.javaClass.superclass
                    .getDeclaredField("mDismissed")
            field.isAccessible = true
            field.set(fragment, mShowing)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    private var startRequest=false

    override fun onStart() {
        super.onStart()
        if(startRequest){
            setMShowing(this,false)
            dialog.dismiss()
            finishAction?.action()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(activity!!.getString(R.string.warning))
        builder.setMessage(activity!!.getString(R.string.warning_message))
        builder.setNegativeButton(R.string.dialog_cancel,this)
        builder.setPositiveButton(R.string.dialog_setting,this)
        return builder.create()
    }

    private var cancelAction: RequestAction?=null

    fun setCancel(action: RequestAction): PermissionDialogFragment{
        cancelAction=action
        return this
    }

    private var finishAction: RequestAction?=null

    fun setFinish(action: RequestAction): PermissionDialogFragment{
        finishAction=action
        return this
    }


}
