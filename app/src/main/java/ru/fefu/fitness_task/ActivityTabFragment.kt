package ru.fefu.fitness_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.fitness_task.databinding.FragmentActivityTabBinding

class ActivityTabFragment : Fragment() {

    private var _binding: FragmentActivityTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout


        val adapter = ActivityTabAdapter(requireActivity())
        viewPager.adapter = adapter


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Моя"
                1 -> "Пользователи"
                else -> throw IllegalArgumentException("Invalid position")
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}