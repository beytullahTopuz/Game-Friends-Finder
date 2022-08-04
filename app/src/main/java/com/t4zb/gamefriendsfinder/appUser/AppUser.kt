package com.t4zb.gamefriendsfinder.appUser

import com.google.firebase.auth.FirebaseAuth

object AppUser {
    private var userId = ""

    fun setUserId(userId: String) {
        if (userId != "")
            this.userId = userId
        else
            this.userId = "dummy"
    }

    fun getUserId() = userId

    fun getFirebaseUser() = FirebaseAuth.getInstance().currentUser
}