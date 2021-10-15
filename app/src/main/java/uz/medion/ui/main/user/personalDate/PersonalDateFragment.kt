package uz.medion.ui.main.user.personalDate

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentPersonalDateBinding
import uz.medion.ui.base.BaseFragment
import java.util.*
import kotlin.collections.ArrayList


class PersonalDateFragment : BaseFragment<FragmentPersonalDateBinding, PersonalDateVM>() {


    var list :ArrayList<String>  = arrayListOf()

    @SuppressLint("SetTextI18n")
    override fun onBound() {
        val c = Calendar.getInstance()

        binding.clDob.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.MySpinnerDatePickerStyle,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    binding.tvDob.text = "$dayOfMonth.$monthOfYear.$year"
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.fire_brick_900))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.tangaroa_900))
        }

        for (i in Constants.getGender().iterator()) {
            list.add(requireContext().getString(i))
        }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item, list
        )
        binding.spinnerGender.adapter = adapter
        binding.spinnerGender.setSelection(0)


    }

    override fun getLayoutResId() = R.layout.fragment_personal_date
    override val vm: PersonalDateVM
        get() = ViewModelProvider(this).get(PersonalDateVM::class.java)


}
