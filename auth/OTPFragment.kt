package com.example.minemanage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.minemanage.Utils.getCurrentUserId
import com.example.minemanage.activity.UsersMainActivity
import com.example.minemanage.databinding.FragmentOTPBinding
import com.example.minemanage.models.Users


import com.example.minemanage.viewmodels.AuthViewModel
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [OTPFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OTPFragment : Fragment() {
    private val viewModel : AuthViewModel by viewModels()
    private lateinit var binding : FragmentOTPBinding
    private lateinit var userNumber : String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOTPBinding.inflate(layoutInflater)
        getUserNumber()
        sendOTP()
        onLoginButtonClicked()
        onBackButtonClick()
        return binding.root

    }

    private fun onLoginButtonClicked() {
        binding.btnLogin.setOnClickListener {
            Utils.showDialog(requireContext(),"Signing you in...")
            val otp = binding.pinview.text.toString()
            if(otp.isEmpty() || otp.length < 6) {
                Utils.showToast(requireContext(),"please enter valid otp")
            }
            else{

                verifyOTP(otp)
            }



        }
    }

    private fun verifyOTP(otp: String) {
        val user = Users(uid = Utils.getCurrentUserId(), userPhoneNumber = userNumber, userAdress = null)

        viewModel.signInWithPhoneAuthCredential(otp, userNumber, user)


        lifecycleScope.launch {
            viewModel.isSignedInSuccessfully.collect{
                if(it){
                    Utils.hideDialog()
                    Utils.showToast(requireContext(),"Logged in successfully")
                    startActivity(Intent(requireContext(), UsersMainActivity::class.java))
                    requireActivity().finish()
                }
            }
        }


    }




    private fun sendOTP() {
        Utils.showDialog(requireContext(), "Sending OTP...")
        viewModel.apply {
            sendOTP(userNumber,requireActivity())
            lifecycleScope.launch {
                otpSent.collect {
                    if (it) {
                        Utils.hideDialog()
                        Utils.showToast(requireContext(),"OTP sent")
                    }
                }
            }

        }


    }

    private fun onBackButtonClick() {
        binding.tbOtpFragment.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_signinFragment)

        }
    }

    private fun getUserNumber() {
        val bundle = arguments
        userNumber = bundle?.getString("number").toString()
        binding.tvuserNumber.text = userNumber
    }


}