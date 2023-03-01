package com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.R
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.databinding.FragmentHomeBinding
import com.ibrahimethemsen.turkiyefinans.turkiyefinans.androidhello.utility.SharedManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedManager: SharedManager
    private lateinit var navController : NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedManager = SharedManager(requireContext())
        updateUI()
        listener()
        val navHostFragment = childFragmentManager.findFragmentById(R.id.home_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navController
        )
        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id){
                R.id.bookDetailFragment -> binding.bottomNavigationView.visibility = View.GONE
                else -> {binding.bottomNavigationView.visibility = View.VISIBLE}
            }
        }
    }

    private fun listener() {
        binding.apply {
            homeSetThemeSc.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    sharedManager.setSharedPreference(IS_DARK_THEME, true)
                    updateUI()
                } else {
                    sharedManager.setSharedPreference(IS_DARK_THEME, false)
                    updateUI()
                }

            }
            homeAddBookFab.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToAddBookFragment()
                findNavController().navigate(action)
            }
        }

    }

    private fun updateUI() {
        binding.homeSetThemeSc.apply {
            isChecked = sharedManager.getSharedPreference(IS_DARK_THEME, false)
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val IS_DARK_THEME = "is_dark_theme"
    }
}