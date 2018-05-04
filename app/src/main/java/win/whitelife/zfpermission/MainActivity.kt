package win.whitelife.zfpermission

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import win.whitelife.permission.PermissionManager
import win.whitelife.permission.interfaces.Action
import win.whitelife.permission.interfaces.RequestCallback

class MainActivity : AppCompatActivity() {

    private val TAG="权限设置"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionManager.with(this)
                .addPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .addPermission(android.Manifest.permission.CAMERA)
                .addPermission(android.Manifest.permission.READ_SMS)
                .setFinishAction(object : Action {
                    override fun action(string: Array<String>?) {
                        Log.d(TAG, "结束啦")
                    }
                })
                .setDeniedAction(object : Action {
                    override fun action(string: Array<String>?) {
                        for(per in string!!.iterator()){
                            Toast.makeText(this@MainActivity,per,Toast.LENGTH_SHORT).show()
                        }
                    }
                })
                .setGrantedAction(object : Action {
                    override fun action(string: Array<String>?) {
                        Log.d(TAG, "通过数量"+string?.size)
                    }
                })
                .start()
    }
}
