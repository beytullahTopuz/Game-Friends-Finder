package com.t4zb.gamefriendsfinder.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.t4zb.gamefriendsfinder.R
import com.t4zb.gamefriendsfinder.appUser.AppUser
import com.t4zb.gamefriendsfinder.core.FireStoreService
import com.t4zb.gamefriendsfinder.databinding.FragmentRegisterBinding
import com.t4zb.gamefriendsfinder.helper.GmsAuthHelper
import com.t4zb.gamefriendsfinder.model.UserModel
import com.t4zb.gamefriendsfinder.ui.viewmodel.SharedViewModel
import com.t4zb.gamefriendsfinder.util.showLogDebug
import com.t4zb.gamefriendsfinder.util.showSnack
import kotlinx.android.synthetic.main.activity_main.view.*


class RegisterFragment : Fragment() {
    private lateinit var mBinding: FragmentRegisterBinding
    private lateinit var mContext: FragmentActivity

    // private val viewModel: LoginViewModel by viewModels()
    private lateinit var mSharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireActivity()
        mBinding = FragmentRegisterBinding.bind(view)

        mSharedViewModel =
            ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(
                SharedViewModel::class.java
            )

        mBinding.registerFragmentGOTOLoginFragment.setOnClickListener {
            var directions = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(directions)
        }

        mBinding.registerFragmentRegisterBTN.setOnClickListener {

            var nameTextField = mBinding.registerFragmentNameTextField.text
            var lastTextField = mBinding.registerFragmentLastNameTextField.text
            var mailTextField = mBinding.registerFragmentLMailTextField.text
            var passwordTextField = mBinding.registerFragmentPasswordTextField.text
            var ageTextField = mBinding.registerFragmentAgeTextField.text

            // fast validation
            if (nameTextField.isNullOrEmpty() or lastTextField.isNullOrEmpty() or mailTextField.isNullOrEmpty() or passwordTextField.isNullOrEmpty() or ageTextField.isNullOrEmpty()) {
                showSnack(view, "please fill in the blanks")
            } else {
                //regiter initialize...

                GmsAuthHelper(
                    mContext,
                    mSharedViewModel
                ).onRegisterWithEmail(mailTextField.toString(), passwordTextField.toString())

                mSharedViewModel.isLoginORisRegister.observe(viewLifecycleOwner, { register_state ->
                    if (register_state) {
                        //userData Save
                        var mUser = UserModel(
                            uid = AppUser.getUserId(),
                            name = nameTextField.toString(),
                            lastName = lastTextField.toString(),
                            mail = mailTextField.toString(),
                            age = ageTextField.toString()
                        )
                        mSharedViewModel.errorMessage.postValue("User Created")
                        mSharedViewModel.isLoginORisRegister.postValue(false)
                        FireStoreService(mContext, mSharedViewModel).addUser(mUser)
                        mSharedViewModel.dbState.observe(viewLifecycleOwner, { db_state ->
                            if (db_state) {
                                //go to home page or profile page with user
                                var direction =
                                    RegisterFragmentDirections.actionRegisterFragmentToProfileFragment()
                                Navigation.findNavController(view).navigate(direction)
                            }
                        })
                    }
                })
                mSharedViewModel.errorMessage.observe(viewLifecycleOwner, {
                    showLogDebug(TAG, it)
                    showSnack(view, it)
                })
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    companion object {

        const val TAG = "REGISTERFRAGMENT"

    }
}