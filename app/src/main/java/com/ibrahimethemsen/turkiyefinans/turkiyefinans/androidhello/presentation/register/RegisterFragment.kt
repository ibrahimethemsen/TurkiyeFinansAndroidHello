package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.register

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentRegisterBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.domain.model.datastore.UserSettings
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.dataStore
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.userInfoMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var gender : String? = null
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
    }
    private fun listener(){
        binding.apply {
            registerBtn.setOnClickListener {
                if (checkEditText()){
                   writeDataStore()
                }else{
                    requireContext().userInfoMessage(R.string.empty_text_field)
                }
            }
        }
        radioButtonListener(binding.registerLady,R.string.lady)
        radioButtonListener(binding.registerGentleman,R.string.gentleman)
    }
    private fun writeDataStore(coroutineContext : CoroutineContext = Dispatchers.IO){
        val deviceId = Settings.Secure.getString(requireContext().contentResolver,Settings.Secure.ANDROID_ID)
        lifecycleScope.launch(coroutineContext){
            requireContext().dataStore.updateData {
                UserSettings(
                    deviceId,
                    binding.registerEpostaEt.text.toString(),
                    binding.registerPasswordEt.text.toString(),
                    binding.registerSecurityPasswordEt.text.toString(),
                    binding.registerNickNameEt.text.toString(),
                    gender
                )
            }
        }
        toLoginFragment()
    }

    private fun radioButtonListener(radioButton : RadioButton,@StringRes resourceString : Int){
        radioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                gender = getString(resourceString)
            }
        }
    }

    private fun checkEditText() : Boolean{
        binding.apply {
            return viewModel.isCheckRegister(
                registerEpostaEt.text.toString(),
                registerPasswordEt.text.toString(),
                registerSecurityPasswordEt.text.toString(),
                registerNickNameEt.text.toString(),
            ) && gender != null
        }
    }
    private fun toLoginFragment(){
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        findNavController().navigate(action)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}