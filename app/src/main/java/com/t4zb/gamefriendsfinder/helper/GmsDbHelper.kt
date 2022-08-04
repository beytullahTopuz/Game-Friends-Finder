package com.t4zb.gamefriendsfinder.helper

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object GmsDbHelper {

    fun rootRef(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }

    fun userRef(): DatabaseReference {
        return rootRef().child("User")
    }

    fun getUser(userID: String): DatabaseReference {
        return rootRef().child("User").child(userID)
    }

    fun gameInfoRef(userID: String): DatabaseReference {
        return rootRef().child("GameInfo").child(userID)
    }

    fun gameFriends(): DatabaseReference {
        return rootRef().child("Friends")
    }

}