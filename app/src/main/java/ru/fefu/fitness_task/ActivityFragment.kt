package ru.fefu.fitness_task

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.fitness_task.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {

    private var _binding: FragmentActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val items = listOf(
            ActivityItem.Section("Вчера"),
            ActivityItem.Activity("Велосипед", "5 км                             1 час 12 минут"),
            ActivityItem.Activity("Бег", "3 км                                               1 час"),
            ActivityItem.Section("2023-10-08"),
            ActivityItem.Activity("Кадилак", "10 км                                             3 часа")
        )


        val adapter = ActivityAdapter(items) { activity ->

            val detailFragment = ActivityDetailFragment.newInstance(activity.title, activity.description)
            parentFragmentManager.commit {
                replace(R.id.fragmentContainerView, detailFragment)
                addToBackStack(null)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.startActivityButton.setOnClickListener {
            val intent = Intent(requireContext(), TrackingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}