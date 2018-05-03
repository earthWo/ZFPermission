package win.whitelife.zfpermission

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import win.whitelife.permission.PermissionManager
import win.whitelife.permission.interfaces.RequestCallback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionManager.with(this)
                .addPermission(android.Manifest.permission.CAMERA)
                .addPermission(android.Manifest.permission.READ_SMS)
                .start()
    }
}
