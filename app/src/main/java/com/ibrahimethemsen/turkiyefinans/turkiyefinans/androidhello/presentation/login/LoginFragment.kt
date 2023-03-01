package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.login

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentLoginBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.domain.model.datastore.UserSettings
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.SharedManager
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.dataStore
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.userInfoMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedManager : SharedManager
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedManager = SharedManager(requireContext())
        clickableText(R.string.register,::toRegisterFragment,binding.loginToRegisterTv)
        clickableText(R.string.forgot_password,::toForgotPasswordFragment,binding.loginForgotPassword)
        listener()
        isUserRemember()
    }
    private fun listener(){
        binding.apply {
            loginBtn.setOnClickListener {
                if (viewModel.isCheckLogin(binding.loginEpostaEt.text.toString(),binding.loginPasswordEt.text.toString())){
                    readDataStore()
                    loginCheck(readDataStore())
                }else{
                    requireContext().userInfoMessage(R.string.empty_text_field)
                }
            }
        }
    }

    private fun isUserRemember(){
        if (sharedManager.getSharedPreference(IS_USER_REMEMBER,false)){
            toHomeFragment()
        }
    }

    private fun readDataStore(coroutineContext : CoroutineContext = Dispatchers.IO) : UserSettings {
        return runBlocking(coroutineContext) {
            requireContext().dataStore.data.first()
        }
    }

    private fun loginCheck(data : UserSettings){
        val checkEposta = data.email
        val checkPassword = data.password
        if (checkEposta.equals(binding.loginEpostaEt.text.toString()) && checkPassword.equals(binding.loginPasswordEt.text.toString())){
            if (binding.loginRememberAccount.isChecked){
                sharedManager.setSharedPreference(IS_USER_REMEMBER,true)
            }
            toHomeFragment()
        }else{
            requireContext().userInfoMessage(R.string.false_email_password)
        }
    }

    private fun toHomeFragment(){
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun clickableText(
        @StringRes resString : Int,
        clickHighOrder : () -> Unit,
        clickText : TextView
    ){
        val resourceString = getString(resString)
        val spannable = SpannableString(resourceString)
        val clickable : ClickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                clickHighOrder.invoke()
            }
        }
        spannable.setSpan(
            clickable,
            0,
            resourceString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        clickText.text = spannable
        clickText.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun toForgotPasswordFragment(){
        val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigate(action)
    }
    private fun toRegisterFragment(){
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        private const val IS_USER_REMEMBER = "is_user_remember"
    }
}