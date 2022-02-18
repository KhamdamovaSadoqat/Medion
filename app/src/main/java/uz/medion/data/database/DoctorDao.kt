package uz.medion.data.database
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import uz.medion.data.model.doctor.DoctorInfo
//import java.util.concurrent.Flow
//
//@Dao
//interface DoctorDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertDoctor(doctor:List<DoctorInfo>)
//    @Query("select * from doctor")
//    fun getDoctor():kotlinx.coroutines.flow.Flow<List<DoctorInfo>>
//}