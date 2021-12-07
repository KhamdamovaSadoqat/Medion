package uz.medion.ui.main.user.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import uz.medion.R
import uz.medion.databinding.FragmentCalendarBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.gone
import uz.medion.utils.visible

class CalendarFragment : BaseFragment<FragmentCalendarBinding,CalendarVM>() {

    private lateinit var timetableAdapter: TimetableAdapter

    override fun onBound() {

        setUp()
    }

    fun setUp(){

        timetableAdapter= TimetableAdapter{}

        binding.rvCalendar.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.rvCalendar.adapter=timetableAdapter

//        val currentDate = CalendarDay.today()
//        val calendarState = binding.cvCalendar.state().edit()
//        calendarState.setMinimumDate(
//            CalendarDay.from(
//                currentDate.year,
//                currentDate.month,
//                currentDate.day
//            )
//        )
//        if (currentDate.month == 12)
//            calendarState.setMaximumDate(CalendarDay.from(currentDate.year + 1, 0, currentDate.day))
//        calendarState.setMaximumDate(
//            CalendarDay.from(
//                currentDate.year,
//                currentDate.month + 1,
//                currentDate.day
//            )
//        )
//        calendarState.commit()
//

    }


    override fun getLayoutResId()=R.layout.fragment_calendar

    override val vm: CalendarVM
        get() = ViewModelProvider(this).get(CalendarVM::class.java)

}