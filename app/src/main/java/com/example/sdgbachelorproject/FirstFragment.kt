package com.example.sdgbachelorproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.FragmentFirstBinding
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.quickstart.auth.kotlin.SignInActivity
import kotlinx.android.synthetic.main.fragment_first.view.*
import javax.inject.Inject

class FirstFragment : Fragment() {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (activity?.application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root

        view.btn_log_out.setOnClickListener {
            signOut()
        }

        view.btn_test_repo.setOnClickListener {
            signInViewModel.printToConsole()
        }

        view.txt_current_user.text = signInViewModel.currentUser.value?.displayName.toString()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun signOut() {
        this.context?.let {
            AuthUI.getInstance()
                .signOut(it)
                .addOnCompleteListener {
                    val intent = Intent(this.activity, SignInActivity::class.java)
                    startActivity(intent)
                }
        }
    }

//    private fun deleteAccount() {
//        AuthUI.getInstance()
//            .delete(this)
//            .addOnCompleteListener {
//                // ...
//            }
//    }
}