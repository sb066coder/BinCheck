package ru.sb066coder.bincheck.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import ru.sb066coder.bincheck.databinding.FragmentBinDetailBinding
import ru.sb066coder.bincheck.viewmodel.MainViewModel


class BinDetail : Fragment() {

    private val viewModel: MainViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBinDetailBinding.inflate(
            inflater,
            container,
            false
        )

        try {
            val bin = arguments?.getString("Bin")?.toIntOrNull() ?: 0

            viewModel.clearCardInfo()

            viewModel.getCardInfo(bin)

            viewModel.data.observe(viewLifecycleOwner) {
                with(binding) {
                    tvTitle.text = "$bin BIN info:"
                    tvNumberLength.text = it.number?.length.toString()
                    tvNumberLuhn.text = if (it.number?.luhn == true) "Yes" else "No"
                    tvScheme.text = it.scheme.orEmpty()
                    tvType.text = it.type.orEmpty()
                    tvBrand.text = it.brand.orEmpty()
                    tvPrepaid.text = if (it.prepaid == true) "Yes" else "No"
                    tvCountry.text = """
                        ${it.country?.emoji.orEmpty()} ${it.country?.name.orEmpty()}
                        currency: ${it.country?.currency.orEmpty()}
                        latitude: ${it.country?.latitude}
                        longitude: ${it.country?.longitude}
                    """.trimIndent()
                    tvBankUrl.text = """
                        ${it.bank?.name.orEmpty()}
                        ${it.bank?.url.orEmpty()}
                        ${it.bank?.city.orEmpty()}
                    """.trimIndent()
                    tvBankPhone.text = it.bank?.phone.orEmpty()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error appeared", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}