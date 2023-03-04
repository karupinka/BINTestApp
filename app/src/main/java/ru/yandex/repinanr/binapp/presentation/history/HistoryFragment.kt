package ru.yandex.repinanr.binapp.presentation.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.yandex.repinanr.binapp.R
import ru.yandex.repinanr.binapp.app.App
import ru.yandex.repinanr.binapp.databinding.FragmentHistoryBinding
import ru.yandex.repinanr.binapp.presentation.*
import javax.inject.Inject

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: HistoryViewModel

    @Inject
    lateinit var adapter: HistoryAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)
        initAdapter()
        viewModel.historyList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                EmptyResultState -> {
                    binding.loader.visibility = GONE
                    binding.emptyHistory.visibility = VISIBLE
                }
                is ErrorState -> {
                    showNoEmptyResult()
                    showError()
                }
                InProgress -> {
                    binding.loader.visibility = VISIBLE
                }
                ResultState -> {
                    showNoEmptyResult()
                }
            }
        }
        viewModel.getHistory()
        binding.clearButton.setOnClickListener {
            viewModel.clearHistory()
            showRemoveSnackbar()
        }
    }

    private fun initAdapter() {
        binding.historyRecycler.adapter = adapter
        binding.historyRecycler.layoutManager = LinearLayoutManager(context)
    }

    private fun showNoEmptyResult() {
        binding.emptyHistory.visibility = GONE
        binding.loader.visibility = GONE
    }

    private fun showError() {
        val snackbar = Snackbar.make(
            binding.root,
            R.string.other_error,
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    private fun showRemoveSnackbar() {
        val snackbar = Snackbar.make(
            binding.root,
            R.string.toast_remove,
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }
}