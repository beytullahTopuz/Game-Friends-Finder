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
import com.t4zb.gamefriendsfinder.databinding.FragmentLoginBinding
import com.t4zb.gamefriendsfinder.helper.GmsAuthHelper
import com.t4zb.gamefriendsfinder.ui.viewmodel.SharedViewModel
import com.t4zb.gamefriendsfinder.util.showLogDebug
import com.t4zb.gamefriendsfinder.util.showLogError
import com.t4zb.gamefriendsfinder.util.showSnack


class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var mContext: FragmentActivity
    private lateinit var mSharedViewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireActivity()
        mBinding = FragmentLoginBinding.bind(view)

        mSharedViewModel =
            ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(
                SharedViewModel::class.java
            )

        mBinding.loginFragmentGOTOregisterFragment.setOnClickListener {
            var direction = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(view).navigate(direction)
        }

        mBinding.loginFragmentLoginBTN.setOnClickListener {
            var mailTextField = mBinding.loginFragmentLMailTextField.text
            var passwordTExtField = mBinding.loginFragmentPasswordTextField.text

            if (mailTextField.toString().isNullOrEmpty() or passwordTExtField.toString()
                    .isNullOrEmpty()
            ) {
                showSnack(view, "please fill in the blanks")
            } else {
                GmsAuthHelper(
                    mContext,
                    mSharedViewModel
                ).onSignInWithEmail(mailTextField.toString(), passwordTExtField.toString())
                mSharedViewModel.isLoginState.observe(viewLifecycleOwner, { login_state ->
                    if (login_state) {
                        showLogDebug(TAG, "login success")
                        val mDirection =
                            LoginFragmentDirections.actionLoginFragmentToProfileFragment()
                        Navigation.findNavController(view).navigate(mDirection)
                    } else {
                        mSharedViewModel.loginMesssageState.observe(viewLifecycleOwner, {
                            showSnack(view, it)
                            showLogError(TAG, "login failed $it")
                        })
                    }
                })
            }
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {

        const val TAG = "LOGINFRAGMENT"
    }
}