package lk.eclk.locationservice.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import lk.eclk.locationservice.models.MediaItem

@Dao
interface MediaItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(mediaItem: MediaItem)
}