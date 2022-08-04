package com.t4zb.gamefriendsfinder.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.t4zb.gamefriendsfinder.R
import com.t4zb.gamefriendsfinder.appUser.AppUser
import com.t4zb.gamefriendsfinder.core.FireStoreService
import com.t4zb.gamefriendsfinder.databinding.FragmentProfileBinding
import com.t4zb.gamefriendsfinder.ui.viewmodel.SharedViewModel

class ProfileFragment : Fragment() {

    private lateinit var mContext: FragmentActivity
    private lateinit var mBinding: FragmentProfileBinding
    private lateinit var mSharedViewModel: SharedViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireActivity()
        mBinding = FragmentProfileBinding.bind(view)

        mSharedViewModel =
            ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(
                SharedViewModel::class.java
            )

        var navController =Navigation.findNavController(mContext,R.id.nav_host_profile)
        NavigationUI.setupWithNavController(mBinding.profileBootomNavigationView,navController)

        FireStoreService(mContext,mSharedViewModel).getUser(AppUser.getUserId())

        mSharedViewModel.currentUser.observe(viewLifecycleOwner,{
            mBinding.userNameText.text = it.name
            mBinding.userIdText.text = it.uid
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        const val TAG = "PROFILEFRAGMENT"
    }
}