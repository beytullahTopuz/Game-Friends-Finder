package com.t4zb.gamefriendsfinder.ui.fragment.profilemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.t4zb.gamefriendsfinder.R
import com.t4zb.gamefriendsfinder.databinding.FragmentAddGameBinding
import com.t4zb.gamefriendsfinder.databinding.FragmentGameInfoBinding
import com.t4zb.gamefriendsfinder.ui.fragment.AddGameFragmentDirections
import com.t4zb.gamefriendsfinder.ui.fragment.ProfileFragment
import com.t4zb.gamefriendsfinder.ui.fragment.ProfileFragmentDirections


class GameInfoFragment : Fragment() {

    private lateinit var mContext: FragmentActivity
    private lateinit var mBinding: FragmentGameInfoBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireActivity()
        mBinding = FragmentGameInfoBinding.bind(view)

      mBinding.addGameButton.setOnClickListener {

        /*  var dirction = GameInfoFragmentDirections.actionGameInfoFragmentToAddGameFragment()
          findNavController().navigate(dirction)
          */


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
        return inflater.inflate(R.layout.fragment_game_info, container, false)
    }

    companion object {

    }
}