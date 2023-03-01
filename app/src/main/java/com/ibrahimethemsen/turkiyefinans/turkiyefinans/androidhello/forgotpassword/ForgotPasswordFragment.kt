package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentForgotPasswordBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.model.UserSettings
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.dataStore
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.userInfoMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class ForgotPasswordFragment : Fragment() {
    private var _binding : FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ForgotPasswordViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
    }
    private fun listener(){
        binding.apply {
            forgotPasswordBtn.setOnClickListener {
                if (viewModel.isCheckForgotPassword(forgotEpostaEt.text.toString(),forgotPasswordEt.text.toString())){
                    checkUser(readDataStore())
                }else{
                    requireContext().userInfoMessage(R.string.empty_text_field)
                }
            }
        }
    }
    private fun readDataStore(coroutineContext : CoroutineContext = Dispatchers.IO) : UserSettings {
        return runBlocking(coroutineContext) {
            requireContext().dataStore.data.first()
        }
    }
    private fun checkUser(data : UserSettings){
        val checkEposta = data.email
        val checkPassword = data.securityPassword
        if (checkEposta.equals(binding.forgotEpostaEt.text.toString()) && checkPassword.equals(binding.forgotPasswordEt.text.toString())){
            requireContext().userInfoMessage(R.string.send_email_link)
            toLoginFragment()
        }else{
            requireContext().userInfoMessage(R.string.false_email_password)
        }
    }

    private fun toLoginFragment(){
        val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}