package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.login

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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentLoginBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickableText(R.string.register,::toRegisterFragment,binding.loginToRegisterTv)
        clickableText(R.string.forgot_password,::toForgotPasswordFragment,binding.loginForgotPassword)
        binding.loginBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val data = requireContext().dataStore.data.first()
                println("data $data")
            }
        }
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
}