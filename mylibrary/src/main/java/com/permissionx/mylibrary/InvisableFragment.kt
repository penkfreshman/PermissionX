package com.permissionx.penk

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

typealias  PermissionCallback=(Boolean,List<String>)->Unit
class InvisableFragment :Fragment(){

    private  var callback:PermissionCallback?=null

    fun requestNow(cb:PermissionCallback,vararg permission: String){
        callback=cb
        requestPermissions(permission,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
       if(requestCode==1){
           val deniedList=ArrayList<String>()
           for((Index,result)in grantResults.withIndex()){
               if (result !=PackageManager.PERMISSION_GRANTED){
                   deniedList.add(permissions[Index])
               }
           }
           val allGranted=deniedList.isEmpty()
           callback?.let { it(allGranted,deniedList) }
       }
    }
}