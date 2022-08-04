package com.t4zb.gamefriendsfinder.ui.fragment.profilemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.t4zb.gamefriendsfinder.R
import com.t4zb.gamefriendsfinder.appUser.AppUser
import com.t4zb.gamefriendsfinder.core.FireStoreService
import com.t4zb.gamefriendsfinder.databinding.FragmentProfileInfoBinding
import com.t4zb.gamefriendsfinder.ui.viewmodel.SharedViewModel


class ProfileInfoFragment : Fragment() {

    private lateinit var mBinding: FragmentProfileInfoBinding
    private lateinit var mContext: FragmentActivity
    private lateinit var mSharedViewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireActivity()
        mBinding = FragmentProfileInfoBinding.bind(view)

        mSharedViewModel =
            ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(
                SharedViewModel::class.java
            )

        //FireStoreService(mContext,mSharedViewModel).getUser(AppUser.getUserId())
        mSharedViewModel.currentUser.observe(viewLifecycleOwner,{
            mBinding.textViewMailText.text = it.mail
            mBinding.textViewGenderText.text = it.gender
            mBinding.textViewAgeText.text = it.age
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
        return inflater.inflate(R.layout.fragment_profile_info, container, false)
    }

    companion object {
        const val TAG = "PROFILEINFOFRAGMENT"
    }
}