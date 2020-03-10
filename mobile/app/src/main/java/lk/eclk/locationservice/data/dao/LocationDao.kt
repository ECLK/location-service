package lk.eclk.locationservice.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lk.eclk.locationservice.models.Location

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(location: Location)

    @Query("SELECT * FROM locations WHERE code = :code;")
    fun getLocation(code: String): Location?

    @Query("SELECT * FROM locations")
    fun getLocations(): LiveData<List<Location>>

    @Query("DELETE FROM locations WHERE code = :code;")
    fun deleteLocation(code: String)

    @Query("DELETE FROM locations")
    fun deleteAll()
}