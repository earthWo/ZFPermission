# ZFPermission
引入方法：
```
compile 'win.whitelife.ZFPermission:permission:0.0.1'
```
使用方式：
```
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
```