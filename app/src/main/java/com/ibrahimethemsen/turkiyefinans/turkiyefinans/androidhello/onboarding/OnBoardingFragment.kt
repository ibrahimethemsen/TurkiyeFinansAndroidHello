package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentOnBoardingBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.SharedManager

class OnBoardingFragment : Fragment() {
    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedManager : SharedManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedManager = SharedManager(requireContext())
        listener()
        checkSkip()
    }
    private fun listener(){
        binding.apply {
            onboardingSkipBtn.setOnClickListener {
                sharedManager.setSharedPreference(ON_BOARDING_SKIP,true)
                onboardingToLogin()
            }
        }
    }
    private fun checkSkip(){
        val isSkip = sharedManager.getSharedPreference(ON_BOARDING_SKIP,false)
        if (isSkip){
            onboardingToLogin()
        }
    }
    private fun onboardingToLogin(){
        val action = OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment()
        findNavController().navigate(action)
    }
    companion object{
        private const val ON_BOARDING_SKIP = "isSkip"
    }
}