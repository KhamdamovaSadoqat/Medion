package uz.medion.ui.main.user.calendar

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applandeo.materialcalendarview.utils.DateUtils
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentCalendarBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.invisible
import uz.medion.utils.visible
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : BaseFragment<FragmentCalendarBinding, CalendarVM>() {

    private lateinit var timetableAdapter: TimetableAdapter

    override fun onBound() {
        setUp()
        getBookingEvent()
    }

    fun setUp() {

        timetableAdapter = TimetableAdapter {}
        binding.rvCalendar.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvCalendar.adapter = timetableAdapter

    }

    private fun getBookingEvent() {
        vm.getBookedEvent().observe(this) { booked ->
            when (booked.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvCalendar.visible()

                    val calendars: MutableList<Calendar> = ArrayList()

                    for(day in booked.data!!.indices){
                        //2022-01-22
                        val responseDate = booked.data[day].bookDay!!.split("-")
                        val bookedDay: Calendar = DateUtils.getCalendar()
                        bookedDay.set(responseDate[0].toInt(),
                            responseDate[1].toInt() - 1,
                            responseDate[2].toInt(),
                            booked.data[day].bookTime!!.hour!!,
                            booked.data[day].bookTime!!.minute!!,
                            booked.data[day].bookTime!!.second!!)
                        calendars.add(bookedDay)
                    }

                    binding.calendarView.setHighlightedDays(calendars)
                    timetableAdapter.setData(booked.data)

                }
                Status.ERROR -> {
                }
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_calendar

    override val vm: CalendarVM
        get() = ViewModelProvider(this).get(CalendarVM::class.java)

}