package ru.fefu.fitness_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import ru.fefu.fitness_task.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragmentContainerView, ChangePasswordFragment())
                addToBackStack(null)
            }
        }

        binding.button3.setOnClickListener {
        }

        binding.button.setOnClickListener {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}