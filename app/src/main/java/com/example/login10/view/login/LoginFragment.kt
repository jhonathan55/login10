package com.example.login10.view.login

import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.login10.R
import com.example.login10.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        //establece los listeners para login
        binding.loginButton.setOnClickListener {
            validateAndLogin()
        }
        //establecer los textWatcher para los campos de entrada
        binding.usernameEditText.addTextChangedListener(loginTextWatcher)
        binding.passwordEditText.addTextChangedListener(loginTextWatcher)
        return binding.root
    }
    private fun validateAndLogin() {
        val username=binding.usernameEditText.text.toString().trim()
        val password=binding.passwordEditText.text.toString().trim()
        var isValid=true
        if (username.isEmpty()){
            binding.usernameTextInputLayout.error="Username is required"
            isValid=false
        }else if (username.length<4){
            binding.usernameTextInputLayout.error="Username must be at least 4 characters"
            isValid=false
        }else{
            binding.usernameTextInputLayout.error=null
        }
        if (password.isEmpty()){
            binding.passwordTextInputLayout.error="Password is required"
            isValid=false
        }else if (password.length<=4){
            binding.passwordTextInputLayout.error="Password must be at least 5 characters"
            isValid=false
        }else{
            binding.passwordTextInputLayout.error=null
        }
        if (isValid){
            if(username=="admin"&&password=="admin") {
                Toast.makeText(requireContext(), "login ok", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }else{
                Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show()
                binding.errorTextView.visibility=View.VISIBLE
                binding.errorTextView.text="Invalid username or password"
            }

        }
    }


    private val loginTextWatcher=object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val username=binding.usernameEditText.text.toString().trim()
            val password=binding.passwordEditText.text.toString().trim()
     binding.loginButton.isEnabled=username.isNotEmpty()&&password.isNotEmpty()
        }
        override fun afterTextChanged(s: android.text.Editable?) {
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        // Navegar al HomeFragment al hacer clic en el botón de login
//        binding.loginButton.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//        }

        // Navegar al RegisterFragment al hacer clic en el botón de register
        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}