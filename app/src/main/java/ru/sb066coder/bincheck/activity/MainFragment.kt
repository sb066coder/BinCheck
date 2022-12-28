package ru.sb066coder.bincheck.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.sb066coder.bincheck.R
import ru.sb066coder.bincheck.databinding.FragmentMainBinding
import ru.sb066coder.bincheck.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = BinAdapter(
            object : OnSelectListener {
                override fun onSelectItem(bin: Int) {
                    findNavController().navigate(
                        R.id.action_main_to_binDetail,
                        Bundle().also { it.putString("Bin", bin.toString()) }
                    )
                }
            }
        )

        binding.rvHistory.adapter = adapter

        viewModel.added.observe(viewLifecycleOwner) {
            viewModel.getHistory()
        }

        viewModel.history.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(
                R.id.action_main_to_binDetail,
                Bundle().also { it.putString("Bin", binding.etInput.text.toString()) }
            )
        }

        return binding.root
    }
}