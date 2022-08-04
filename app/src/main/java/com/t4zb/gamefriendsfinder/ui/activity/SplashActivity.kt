package com.t4zb.gamefriendsfinder.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.t4zb.gamefriendsfinder.MainActivity
import com.t4zb.gamefriendsfinder.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        var view = mBinding.root
        setContentView(view)

        supportActionBar?.hide()
        mBinding.spastLottieAnim.playAnimation()


        Handler().postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },2500)



    }
}