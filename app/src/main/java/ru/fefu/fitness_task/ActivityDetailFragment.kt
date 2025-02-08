package ru.fefu.fitness_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.fefu.fitness_task.databinding.FragmentActivityDetailBinding

class ActivityDetailFragment : Fragment() {

    private var _binding: FragmentActivityDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(title: String, description: String): ActivityDetailFragment {
            val fragment = ActivityDetailFragment()
            val args = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val title = arguments?.getString(ARG_TITLE) ?: ""
        val description = arguments?.getString(ARG_DESCRIPTION) ?: ""

        binding.detailTitle.text = title
        binding.detailDescription.text = description

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}