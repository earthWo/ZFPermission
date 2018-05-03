package win.whitelife.permission

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle

/**
 * @author wuzefeng
 * 2018/5/3
 */
class PermissionDialogFragment: DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context!!.getString(R.string.warning))
        builder.setMessage(context!!.getString(R.string.warning_message))
        builder.setNegativeButton(R.string.dialog_cancel,null)
        builder.setPositiveButton(R.string.dialog_setting,null)
        return builder.create()
    }


}
