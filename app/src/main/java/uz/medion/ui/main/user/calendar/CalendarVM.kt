package uz.medion.ui.main.user.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.BookingResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class CalendarVM :BaseVM() {

    private val repo = Repository()
    private val bookingEventResponse = MutableLiveData<Resource<List<BookingResponse>>>()

    fun bookingEvent(): LiveData<Resource<List<BookingResponse>>>{
        repo.bookedEvent(bookingEventResponse)
        return bookingEventResponse
    }

}