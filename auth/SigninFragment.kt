package com.example.minemanage

import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.minemanage.databinding.FragmentSigninBinding


class SigninFragment : Fragment() {
    private lateinit var binding : FragmentSigninBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        getUserNumber()
        onContinueButtonClick()
        return binding.root
    }

    private fun onContinueButtonClick() {
        binding.imgsignin.setOnClickListener {
            val number = binding.etuserNumber.text.toString()
            if (number.isEmpty() || number.length < 10 || number.length >10) {
                Utils.showToast(requireContext(), "Enter valid number")

            }
            else {
                val bundle = Bundle()
                bundle.putString("number", number)
                findNavController().navigate(R.id.action_signinFragment_to_OTPFragment, bundle)
            }

        }


    }

    private fun getUserNumber() {


    }


}