package ru.yandex.repinanr.binapp.presentation.info

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.yandex.repinanr.binapp.R
import ru.yandex.repinanr.binapp.app.App
import ru.yandex.repinanr.binapp.databinding.FragmentBinInfoBinding
import ru.yandex.repinanr.binapp.domain.model.BinInfo
import ru.yandex.repinanr.binapp.presentation.*
import javax.inject.Inject

class BinInfoFragment : Fragment() {
    private lateinit var binding: FragmentBinInfoBinding
    private lateinit var viewModel: BinInfoViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.inputType = InputType.TYPE_CLASS_NUMBER
        viewModel = ViewModelProvider(this, factory).get(BinInfoViewModel::class.java)

        setHistoryButtonListener()
        setSearchViewListener()
        setBinInfoObserve()
        setStateObserve()
    }

    private fun setSearchViewListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getHistoryItemUsecase(binding.searchView.query.toString())
                hideSoftKeyboard(requireActivity())
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

        })
    }

    private fun setHistoryButtonListener() {
        binding.historyButton.setOnClickListener {
            findNavController().navigate(BinInfoFragmentDirections.actionBinInfoFragmentToHistoryFragment())
        }
    }

    private fun setStateObserve() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                EmptyResultState -> {
                    throw NoSuchMethodError()
                }
                is ErrorState -> {
                    binding.progressBar.visibility = GONE
                    showError(it.message)
                }
                InProgress -> {
                    binding.progressBar.visibility = VISIBLE
                }
                ResultState -> {
                    binding.progressBar.visibility = GONE
                }
            }
        }
    }

    private fun setBinInfoObserve() {
        viewModel.binInfo.observe(viewLifecycleOwner) {
            it?.let {
                updateInfo(it)
            }
        }
    }

    private fun updateInfo(binInfo: BinInfo) {
        with(binding) {
            cardLengthValue.text =
                binInfo.number?.length?.toString() ?: getString(R.string.no_info_character)
            cardLuhnValue.text =
                binInfo.number?.luhn?.toString() ?: getString(R.string.no_info_character)
            cardSchemeValue.text =
                binInfo.scheme ?: getString(R.string.no_info_character)
            cardTypeValue.text =
                binInfo.type ?: getString(R.string.no_info_character)
            cardPrepaidValue.text =
                binInfo.prepaid?.toString() ?: getString(R.string.no_info_character)
            cardNameValue.text = binInfo.country?.let {
                String.format(getString(R.string.country_format), it.emoji, it.name)
            } ?: getString(R.string.no_info_character)
            with(cardLatLongValue) {
                text = binInfo.country?.let {
                    String.format(getString(R.string.lat_long_format), it.latitude, it.longitude)
                } ?: getString(R.string.no_info_character)
                binInfo.country?.let {
                    val mapIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:${it.latitude}, ${it.longitude}")
                    )
                    setOnClickListener {
                        startActivity(mapIntent)
                    }
                }
            }
            cardBankName.text = binInfo.bank?.name ?: getString(R.string.no_info_character)
            with(cardBankUrl) {
                text = binInfo.bank?.url ?: getString(R.string.no_info_character)
                binInfo.bank?.url?.let {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://$it")
                    )
                    setOnClickListener {
                        startActivity(browserIntent)
                    }
                    setTextColor(Color.BLUE)
                }
            }
            with(cardBankPhone) {
                text = binInfo.bank?.phone ?: getString(R.string.no_info_character)
                binInfo.bank?.phone?.let {
                    val phoneIntent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:$it")
                    )
                    setOnClickListener {
                        startActivity(phoneIntent)
                    }
                }
            }
        }
    }

    private fun showError(@StringRes message: Int) {
        val snackbar = Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }
}