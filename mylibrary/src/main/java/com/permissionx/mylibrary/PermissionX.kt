package com.permissionx.penk

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object  PermissionX {
    private const val TAG="InvisibleFragment"

    fun request(activity:FragmentActivity,vararg  permissions:String,callback: PermissionCallback){
        val fragmentManager=activity.supportFragmentManager
        val existFragment=fragmentManager.findFragmentByTag(TAG)

        val fragment=if (existFragment !=null){
            existFragment as InvisableFragment
        }else{
            val invisableFragment=InvisableFragment()
            fragmentManager.beginTransaction().add(invisableFragment, TAG).commitNow()
            invisableFragment
        }
        fragment.requestNow(callback,*permissions)
    }
}