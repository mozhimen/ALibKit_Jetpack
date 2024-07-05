package com.mozhimen.libk.jetpack.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.mozhimen.basick.elemk.androidx.appcompat.bases.viewbinding.BaseActivityVB
import com.mozhimen.basick.utilk.android.view.applyGone
import com.mozhimen.basick.utilk.android.view.applyVisible
import com.mozhimen.libk.jetpack.test.databinding.ActivityMainBinding

class MainActivity : BaseActivityVB<ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        vb.button1.setOnClickListener {
            Log.d(TAG, "initView: vb.group1.isVisible ${vb.group1.isVisible}")
            if (vb.group1.visibility == View.GONE){
                vb.group1.applyVisible()
            }else{
                vb.group1.applyGone()
            }
        }

        vb.button2.setOnClickListener {
            Log.d(TAG, "initView: vb.group2.isVisible ${vb.group2.isVisible}")
            if (vb.group2.visibility == View.GONE){
                vb.group2.applyVisible()
            }else{
                vb.group2.applyGone()
            }
        }
    }
}