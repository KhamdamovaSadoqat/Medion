package uz.medion.data.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import retrofit2.Converter
//import uz.medion.data.model.doctor.DoctorInfo
//
//
//@Database(entities = [DoctorInfo::class], version = 1, exportSchema = false)
//@TypeConverters(Converter::class)
//abstract class DoctorDatabase ():RoomDatabase(){
//    abstract val doctorDao: DoctorDao
//
//    companion object {
//        private var INSTANCE:DoctorDatabase?=null
//        fun createDatabase(context:Context):DoctorDatabase{
//if (INSTANCE!=null){
//    return INSTANCE!!
//}
//            synchronized(this){
//                INSTANCE =Room.databaseBuilder(context,DoctorDatabase::class.java,"doctor").build()
//                return INSTANCE!!
//            }
//        }
//    }
//}