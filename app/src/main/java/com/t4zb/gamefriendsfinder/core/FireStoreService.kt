package com.t4zb.gamefriendsfinder.core

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.t4zb.gamefriendsfinder.helper.GmsDbHelper
import com.t4zb.gamefriendsfinder.model.UserModel
import com.t4zb.gamefriendsfinder.ui.viewmodel.SharedViewModel
import com.t4zb.gamefriendsfinder.util.showLogDebug
import com.t4zb.gamefriendsfinder.util.showLogError

class FireStoreService(context: Context, sharedViewModel: SharedViewModel) {

    private val context = context
    private val mSharedViewModel = sharedViewModel



    fun addUser(userModel: UserModel){

        userModel.uid?.let {
            GmsDbHelper.userRef().child(it).setValue(userModel).addOnCompleteListener { task_result->
                if (task_result.isSuccessful){
                    mSharedViewModel.dbState.postValue(true)
                    showLogDebug(TAG,"success on add user")
                }else{
                    mSharedViewModel.dbState.postValue(false)
                    showLogError(TAG,task_result.exception?.message.toString())
                }

            }
        }

    }

    fun getUser(id: String){

        GmsDbHelper.getUser(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    var data = p0.getValue(UserModel::class.java)
                    showLogDebug(TAG,data.toString())
                    mSharedViewModel.currentUser.postValue(data)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
               showLogError(TAG,p0.message.toString())
            }

        })
    }


    companion object{
        const val TAG = "FIRESTORESERVICE"
    }

}