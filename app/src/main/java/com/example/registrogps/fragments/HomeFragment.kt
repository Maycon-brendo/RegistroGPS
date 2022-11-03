//package com.example.registrogps.fragments
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.registrogps.R
//import com.example.registrogps.databinding.FragmentHomeBinding
//import com.example.registrogps.utils.getLoginFromSharedPrefs
//import com.example.registrogps.utils.nav
//
//
//class HomeFragment : Fragment() {
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val view = binding.root
//
//        setup()
//
//        return view
//
//
//    }
//
//    private fun setup() {
//        setupClickListeners()
//        setupconfig()
//    }
//
//    private fun setupconfig() {
//
//    }
//
//    private fun setupClickListeners() {
//
//        binding.btnTelaLista.setOnClickListener {
//            nav(R.id.action_homeFragment_to_listaRegistroFragment)
//        }
//
//        binding.fabConfigs.setOnClickListener {
//            nav(R.id.action_homeFragment_to_configActivity)
//        }
//
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
//
//
//
