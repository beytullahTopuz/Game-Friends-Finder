package com.t4zb.gamefriendsfinder.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.t4zb.gamefriendsfinder.model.UserModel

class SharedViewModel(app: Application) :AndroidViewModel(app) {

    //only for register
    // TODO: fix variable name field
    var errorMessage = MutableLiveData<String>()
    var isLoginORisRegister = MutableLiveData<Boolean>()
    var dbState = MutableLiveData<Boolean>()

    // only for login
    var isLoginState = MutableLiveData<Boolean>()
    var loginMesssageState = MutableLiveData<String>()


    var currentUser = MutableLiveData<UserModel>()

}