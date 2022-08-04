package com.t4zb.gamefriendsfinder.helper

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.t4zb.gamefriendsfinder.appUser.AppUser
import com.t4zb.gamefriendsfinder.ui.viewmodel.SharedViewModel
import com.t4zb.gamefriendsfinder.util.showLogDebug
import com.t4zb.gamefriendsfinder.util.showLogError

class GmsAuthHelper(context: Context, mSharedViewModel: SharedViewModel) {

    lateinit var firabaseAuth: FirebaseAuth
    val context = context
    private val mSharedViewModel = mSharedViewModel


    fun onSignInWithEmail(email: String, password: String){
        firabaseAuth = FirebaseAuth.getInstance()

        firabaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                showLogDebug(TAG,"Login success")
                mSharedViewModel.isLoginState.postValue(true)
                mSharedViewModel.loginMesssageState.postValue("success")
                AppUser.setUserId(it.result!!.user!!.uid.toString())
            }else{
                val message =it.exception?.message.toString()
                showLogError(TAG,message)
                mSharedViewModel.isLoginState.postValue(false)
                mSharedViewModel.loginMesssageState.postValue(message)
            }
        }
    }

    fun onRegisterWithEmail(email: String, password: String) {

        firabaseAuth = FirebaseAuth.getInstance()

        firabaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {


            if (it.isSuccessful) {
                AppUser.setUserId(it.result!!.user!!.uid.toString())
                showLogDebug(TAG, "register success")
                mSharedViewModel.isLoginORisRegister.postValue(true)

            } else {
                val errMessage = it.exception?.message.toString()
                mSharedViewModel.errorMessage.postValue(errMessage)
                mSharedViewModel.isLoginORisRegister.postValue(false)
            }

        }

    }

    companion object {
        const val TAG = "GMSAUTHHELPER"
    }

}